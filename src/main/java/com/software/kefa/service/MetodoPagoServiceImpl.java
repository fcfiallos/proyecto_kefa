package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.Random;
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

import jakarta.transaction.Transactional;

@Service
public class MetodoPagoServiceImpl implements IMetodoPagoService {

    @Autowired
    private IMetodoPagoRepository metodoPagoRepository;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(Pago pago, MetodoPagoTO metodoPagoTO, String nickname, CarritoCompra carritoCompra) {
        if (carritoCompra == null) {
            throw new MensajeExisteExcepcion("No existe carrito de compra");
        }
        
        if (validarTarjeta(metodoPagoTO)) {
            Usuario usuario = usuarioRepository.seleccionarPorNickname(nickname);
            pago.setUsuario(usuario);
            String comprobante = UUID.randomUUID().toString();
            pago.setComprobante(comprobante);
            pago.setEstado("Pagado");
            pago.setFecha(LocalDateTime.now());
            Orden orden = carritoCompra.getDetalleOrden().get(0).getOrden();
            pago.setOrden(orden);
            this.metodoPagoRepository.insertar(pago);
            actualizarCantidadProducto(pago);
        } else {
            throw new MensajeExisteExcepcion("No se realizó la transacción");
        }
    }

    private boolean validarTarjeta(MetodoPagoTO metodoPagoTO) {
        try {
            Predicate<MetodoPagoTO> validarTarjeta = tarjeta -> !tarjeta.getNumeroTarjeta().isEmpty()
                    && !tarjeta.getNombreTarjeta().isEmpty() && !tarjeta.getFechaVencimiento().isEmpty()
                    && !tarjeta.getCvv().isEmpty();
            Random random = new Random();
            if (validarTarjeta.test(metodoPagoTO)) {
                return random.nextBoolean();
            }
        } catch (MensajeExisteExcepcion e) {
            throw new MensajeExisteExcepcion("Tarjeta no válida. Intente nuevamente.");
        }
        return false;
    }

    private void actualizarCantidadProducto(Pago pago) {
        pago.getOrden().getDetalleOrden().forEach(detalle -> {
            try {
                productoService.actualizarStock(detalle.getProducto().getId(), -detalle.getCantidad());
            } catch (Exception e) {
                throw new MensajeExisteExcepcion("No se pudo actualizar el stock del producto");
            }  
        });
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

}
