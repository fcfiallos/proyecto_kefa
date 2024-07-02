package com.software.kefa.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.IDetalleOrdenRepository;
import com.software.kefa.repository.IOrdenRepository;
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.DetalleOrden;
import com.software.kefa.repository.modelo.Orden;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.repository.modelo.modelosdto.OrdenDTO;

import jakarta.transaction.Transactional;

@Service
public class OrdenServiceImpl implements IOrdenService {

    @Autowired
    private IOrdenRepository ordenRepository;

    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Orden crearOrdenDePago(String nickname, CarritoCompra carrito) {
        Usuario usuario = this.usuarioRepository.seleccionarPorNickname(nickname);
        carrito = usuario.getCarritoCompra();
        BigDecimal totalOrden = BigDecimal.ZERO;
        BigDecimal costoEnvio = new BigDecimal("15.00");
        List<DetalleOrden> detallesGestionados = new ArrayList<>();
        BigDecimal totalProducto = BigDecimal.ZERO;

        for (DetalleOrden detalle : carrito.getDetalleOrden()) {
            totalProducto = calcularTotalProducto(detalle);
            detalle.setTotalProducto(totalProducto);


            // Si el DetalleOrden ya existe, verifica si necesita ser reatachado al contexto
            // de persistencia
            if (detalle.getId() != null) {
                // Usa merge para asegurar que la entidad esté manejada por el contexto de
                // persistencia
                DetalleOrden detalleGestionado = detalleOrdenRepository.guardarOActualizarDetalleOrden(detalle);
                detallesGestionados.add(detalleGestionado);
            } else {
                // Para nuevas entidades, simplemente añádelas a la lista
                detallesGestionados.add(detalle);
            }

            totalOrden = totalOrden.add(totalProducto).add(costoEnvio);
        }

        Orden orden = new Orden();
        orden.setFecha(LocalDateTime.now());
        orden.setEstado("PENDIENTE");
        orden.setTotalOrden(totalOrden);
        orden.setTotalDetalleOrden(totalProducto);
        orden.setCostoEnvio(costoEnvio);
        String codigoUnico = UUID.randomUUID().toString();
        orden.setCodigo(codigoUnico);
        orden.setDetalleOrden(detallesGestionados);
        orden.setUsuario(usuario);
        ordenRepository.insertar(orden);

        return orden;
    }

    private BigDecimal calcularTotalProducto(DetalleOrden detalle) {
        // detalle = this.detalleOrdenRepository.seleccionarPorIdCarrito(carritoId);
        BigDecimal precioBase = detalle.getProducto().getPrecio().multiply(new BigDecimal(detalle.getCantidad()));
        BigDecimal descuento = detalle.getDescuento() != null ? detalle.getDescuento() : BigDecimal.ZERO;
        BigDecimal precioConDescuento = precioBase.subtract(descuento);
        BigDecimal impuesto = precioConDescuento.multiply(new BigDecimal("0.15")); // IVA en Ecuador
        
          detalle.setTotalProducto(precioConDescuento.add(impuesto));
          detalle.setDescuento(descuento);
          detalle.setImpuesto(impuesto);
          detalle.setPrecioUnitario(detalle.getProducto().getPrecio());
          //detalleOrdenRepository.actualizar(detalle);
        
        return precioConDescuento.add(impuesto);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(Orden orden) {
        this.ordenRepository.insertar(orden);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(Orden orden) {
        this.ordenRepository.actualizar(orden);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Orden buscarPorId(Integer id) {
        return this.ordenRepository.seleccionarPorId(id);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Orden buscarTodo() {
        return this.ordenRepository.seleccionarTodo();
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Orden buscarPorCodigo(String codigo) {
        return this.ordenRepository.seleccionarPorCodigo(codigo);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<OrdenDTO> buscarTodos() {
        return this.ordenRepository.seleccionarTodos();
    }

}