package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.excepcion.MensajeExisteExcepcion;
import com.software.kefa.repository.IMetodoPagoRepository;
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Orden;
import com.software.kefa.repository.modelo.Pago;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.service.modelosto.MetodoPagoTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class MetodoPagoServiceImpl implements IMetodoPagoService {

    @Autowired
    private IMetodoPagoRepository metodoPagoRepository;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Guarda un objeto Pago en la base de datos.
     * 
     * @param pago El objeto Pago a guardar.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(Pago pago) {
        this.metodoPagoRepository.insertar(pago);
    }

    /**
     * Deletes a payment from the system.
     *
     * @param pago The payment to be deleted.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void eliminar(Pago pago) {
        this.metodoPagoRepository.eliminar(pago);
    }

    /**
     * Busca un pago por su ID.
     *
     * @param id el ID del pago a buscar
     * @return el pago encontrado, o null si no se encuentra ningún pago con el ID
     *         especificado
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Pago buscarPorId(Integer id) {
        return metodoPagoRepository.seleccionarPorId(id);
    }

    /**
     * Actualiza un pago en el sistema.
     *
     * @param pago El objeto de tipo Pago que se desea actualizar.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(Pago pago) {
        this.metodoPagoRepository.actualizar(pago);
    }

    /**
     * Sends the payment for validation and updates the necessary entities.
     *
     * @param metodoPagoTO  The payment method details.
     * @param nickname      The nickname of the user making the payment.
     * @param carritoCompra The shopping cart associated with the payment.
     * @param orden         The order associated with the payment.
     * @throws MensajeExisteExcepcion if the shopping cart or order does not exist.
     * @throws MensajeExisteExcepcion if the transaction was not successful.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void enviarValidacion(MetodoPagoTO metodoPagoTO, String nickname, CarritoCompra carritoCompra, Orden orden) {
        Pago pago = new Pago();
        if (carritoCompra == null || orden == null) {
            throw new MensajeExisteExcepcion("No existe carrito de compra u orden de pago");
        }

        if (validarTarjeta(metodoPagoTO)) {
            Usuario usuario = usuarioRepository.seleccionarPorNickname(nickname);
            pago.setUsuario(usuario);
            String comprobante = UUID.randomUUID().toString();
            pago.setComprobante(comprobante);
            pago.setEstado("Pagado");
            pago.setFecha(LocalDateTime.now());

            orden.setEstado("PAGADO");
            orden.setPago(pago);
            Orden ordenActualizada = this.entityManager.merge(orden);

            pago.setOrden(ordenActualizada);
            actualizarCantidadProducto(carritoCompra);
            this.metodoPagoRepository.insertar(pago);
        } else {
            throw new MensajeExisteExcepcion("No se realizó la transacción");
        }
    }

    /**
     * Validates a payment method.
     *
     * @param metodoPagoTO The payment method to validate.
     * @return true if the payment method is valid, false otherwise.
     * @throws MensajeExisteExcepcion if the payment method is not valid.
     */
    private boolean validarTarjeta(MetodoPagoTO metodoPagoTO) {
        try {
            Predicate<MetodoPagoTO> validarTarjeta = tarjeta -> !tarjeta.getNumeroTarjeta().isEmpty()
                    && !tarjeta.getNombreTarjeta().isEmpty() && !tarjeta.getFechaVencimiento().isEmpty()
                    && !tarjeta.getCvv().isEmpty();
            if (validarTarjeta.test(metodoPagoTO)) {
                return true;
            }
        } catch (MensajeExisteExcepcion e) {
            throw new MensajeExisteExcepcion("Tarjeta no válida. Intente nuevamente.");
        }
        return false;
    }

    /**
     * Actualiza la cantidad de productos en el stock del carrito de compra.
     * Si el carrito de compra tiene un ID válido, se actualiza el stock de cada producto en el detalle de la orden.
     * Si el carrito de compra no tiene un ID válido, se lanza una excepción de estado ilegal.
     *
     * @param carritoCompra el carrito de compra que contiene los productos y la cantidad a actualizar en el stock
     * @throws MensajeExisteExcepcion si no se puede actualizar el stock del producto
     * @throws IllegalStateException si el pago o la orden del pago es nulo
     */
    private void actualizarCantidadProducto(CarritoCompra carritoCompra) {
        if (carritoCompra.getId() != null) {
            carritoCompra.getDetalleOrden().forEach(detalle -> {
                try {
                    productoService.actualizarStock(detalle.getProducto().getId(), -detalle.getCantidad());
                } catch (MensajeExisteExcepcion e) {
                    throw new MensajeExisteExcepcion("No se pudo actualizar el stock del producto");
                }
            });
        } else {
            throw new IllegalStateException("Pago o la orden del pago es nulo");
        }
    }
}
