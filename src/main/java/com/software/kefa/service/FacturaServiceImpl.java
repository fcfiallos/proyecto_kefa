package com.software.kefa.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.excepcion.MensajeExisteExcepcion;
import com.software.kefa.repository.IFacturaRepository;
import com.software.kefa.repository.INotificacionRepository;
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Factura;
import com.software.kefa.repository.modelo.Notificacion;
import com.software.kefa.repository.modelo.Orden;
import com.software.kefa.repository.modelo.Usuario;

import jakarta.transaction.Transactional;

@Service
public class FacturaServiceImpl implements IFacturaService {
    @Autowired
    private IFacturaRepository facturaRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private INotificacionRepository notificacionRepository;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Factura buscarPorId(Integer id) {
        return this.facturaRepository.seleccionarPorId(id);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(Factura factura) {
        this.facturaRepository.insertar(factura);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(Factura factura) {
        this.facturaRepository.actualizar(factura);
    }

    /**
     * Envía una factura para un carrito de compra y una orden específica.
     * 
     * @param carritoCompra el carrito de compra asociado a la factura
     * @param nickname el nickname del usuario que realiza la compra
     * @param orden la orden asociada a la factura
     * @return la factura generada y enviada
     * @throws MensajeExisteExcepcion si el carrito de compra o la orden son nulos
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Factura enviarFactura(CarritoCompra carritoCompra, String nickname, Orden orden) {
        Factura factura = new Factura();

        if (carritoCompra == null || orden == null){
            throw new MensajeExisteExcepcion("No se puede enviar la factura, carrito o orden nulos");
        }

        factura.setFechaEmision(LocalDateTime.now());

        BigDecimal impuesto = orden.getTotalOrden().multiply(new BigDecimal(0.15)).setScale(2, RoundingMode.HALF_UP);
        factura.setImpuesto(impuesto);

        factura.setTotal(orden.getTotalOrden());

        BigDecimal subtotal=orden.getTotalOrden().subtract(impuesto);
        factura.setSubtotal(subtotal);
        factura.setOrden(orden);

        long timestamp = System.currentTimeMillis();
        Random random = new Random();
        int randomNumber = random.nextInt(9999);
        String numeroFactura = String.format("%d-%04d", timestamp, randomNumber);
        factura.setNumero(numeroFactura);

        Usuario usuario = this.usuarioRepository.seleccionarPorNickname(nickname);
        factura.setUsuario(usuario);

        Notificacion notificacion = new Notificacion();
        notificacion.setFecha(LocalDateTime.now());
        notificacion.setMensaje("Se ha generado una nueva factura con número: [" + numeroFactura + "]");
        notificacion.setTipo("Factura");
        notificacion.setUsuario(usuario);
        this.notificacionRepository.insertar(notificacion);

        this.facturaRepository.insertar(factura);
        return factura;
    }

}
