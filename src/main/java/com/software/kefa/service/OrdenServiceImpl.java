package com.software.kefa.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.excepcion.MensajeExisteExcepcion;
import com.software.kefa.repository.IDetalleOrdenRepository;
import com.software.kefa.repository.IEnvioRepository;
import com.software.kefa.repository.IOrdenRepository;
import com.software.kefa.repository.IProductoRepository;
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.DetalleOrden;
import com.software.kefa.repository.modelo.Envio;
import com.software.kefa.repository.modelo.Orden;
import com.software.kefa.repository.modelo.Usuario;

import jakarta.transaction.Transactional;

@Service
public class OrdenServiceImpl implements IOrdenService {

    @Autowired
    private IOrdenRepository ordenRepository;

    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private IEnvioRepository envioRepository;

    /**
     * Creates an order of payment for a given user and shopping cart.
     *
     * @param nickname The nickname of the user.
     * @param carrito The shopping cart containing the items to be ordered.
     * @return The created order of payment.
     * @throws MensajeExisteExcepcion If the shopping cart is null or if there is insufficient stock for a product.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Orden crearOrdenDePago(String nickname, CarritoCompra carrito) {
        if (carrito == null) {
            throw new MensajeExisteExcepcion("No se puede crear una orden de pago sin un carrito de compra");     
        }

        Usuario usuario = this.usuarioRepository.seleccionarPorNickname(nickname);
        carrito = usuario.getCarritoCompra();
        BigDecimal totalOrden = BigDecimal.ZERO;
        BigDecimal costoEnvio = new BigDecimal("15.00");
        List<DetalleOrden> detallesGestionados = new ArrayList<>();
        BigDecimal totalProducto = BigDecimal.ZERO;
        Orden orden = new Orden();

        ordenRepository.insertar(orden);
        for (DetalleOrden detalle : carrito.getDetalleOrden()) {

            // Verificar si la cantidad producto existe
            Integer stockActual = productoRepository.seleccionarPorCantidadProductos(detalle.getProducto().getId());

            // Verificar si el stock es suficiente
            if (detalle.getCantidad() > stockActual) {
                // Manejar stock insuficiente (ejemplo: lanzar excepción)
                throw new MensajeExisteExcepcion("Stock insuficiente para el producto: " +
                        detalle.getProducto().getNombre());
            }

            totalProducto = calcularTotalProducto(detalle);
            detalle.setTotalProducto(totalProducto);
            detalle.setOrden(orden);

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

        Envio envio = new Envio();
        envio.setDireccion(usuario.getUbicacion().getDireccion());
        envio.setEstado("ENVIADO");
        envio.setFecha(LocalDateTime.now());
        envio.setTipo("DOMICILIO");
        envio.setUsuario(usuario);

        orden.setFecha(LocalDateTime.now());
        orden.setEstado("PENDIENTE");
        orden.setTotalOrden(totalOrden);
        orden.setTotalDetalleOrden(totalProducto);
        orden.setCostoEnvio(costoEnvio);

        String codigoUnico = UUID.randomUUID().toString();
        orden.setCodigo(codigoUnico);

        orden.setDetalleOrden(detallesGestionados);
        orden.setUsuario(usuario);
        envio.setOrden(orden);


        this.envioRepository.insertar(envio);

        return orden;
    }

    /**
     * Calculates the total price of a product in an order, taking into account the quantity, base price, discount, and tax.
     * 
     * @param detalle The order detail containing the product, quantity, discount, and tax information.
     * @return The total price of the product, including any applicable taxes.
     */
    private BigDecimal calcularTotalProducto(DetalleOrden detalle) {
        BigDecimal precioBase = detalle.getProducto().getPrecio().multiply(new BigDecimal(detalle.getCantidad()));
        BigDecimal descuento = detalle.getDescuento() != null ? detalle.getDescuento() : BigDecimal.ZERO;
        BigDecimal precioConDescuento = precioBase.subtract(descuento);
        BigDecimal impuesto = precioConDescuento.multiply(new BigDecimal("0.15")); // IVA en Ecuador

        detalle.setTotalProducto(precioConDescuento.add(impuesto));
        detalle.setDescuento(descuento);
        detalle.setImpuesto(impuesto);
        detalle.setPrecioUnitario(detalle.getProducto().getPrecio());

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


}