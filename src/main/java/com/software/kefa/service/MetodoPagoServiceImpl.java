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

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(Pago pago) {
        this.metodoPagoRepository.insertar(pago);
    }

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

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void eliminar(Pago pago) {
        this.metodoPagoRepository.eliminar(pago);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Pago buscarPorId(Integer id) {
        return metodoPagoRepository.seleccionarPorId(id);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(Pago pago) {
        this.metodoPagoRepository.actualizar(pago);
    }

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

            Orden ordenActualizada = this.entityManager.merge(orden);

            pago.setOrden(ordenActualizada);
            actualizarCantidadProducto(carritoCompra);
            this.metodoPagoRepository.insertar(pago);
        } else {
            throw new MensajeExisteExcepcion("No se realizó la transacción");
        }
    }

}
