package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.excepcion.MensajeExisteExcepcion;
import com.software.kefa.repository.ICarritoCompraRepository;
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.repository.modelo.Usuario;

import jakarta.transaction.Transactional;

/**
 * This class implements the {@link ICarritoCompraService} interface and
 * provides the implementation
 * for managing the shopping cart operations.
 */
@Service
public class CarritoCompraServiceImpl implements ICarritoCompraService {

    @Autowired
    private ICarritoCompraRepository carritoCompraRepository;


    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void agregarProducto(Integer carritoId, Producto producto, String nickname) {
        CarritoCompra carrito = carritoCompraRepository.seleccionarPorId(carritoId);
        Usuario usuario = iUsuarioRepository.seleccionarPorNickname(nickname);
        if (carrito == null) {
            carrito = new CarritoCompra();
            carrito.setId(carritoId);
            carrito.setFechaSeleccionada(LocalDateTime.now());
            carrito.setCantidad(0);
            carrito.setProductos(new ArrayList<>());
            carrito.setUsuario(usuario);
            carritoCompraRepository.insertar(carrito);
        }/*else {
            throw new MensajeExisteExcepcion("El carrito de compra ya existe.");
        }*/
        carrito.agregarProducto(producto);
        carritoCompraRepository.actualizar(carrito);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void eliminarProducto(Integer carritoId, Producto producto) {
        CarritoCompra carrito = carritoCompraRepository.seleccionarPorId(carritoId);
        if (carrito != null) {
            carrito.eliminarProducto(producto);
            carritoCompraRepository.actualizar(carrito);
        } else {
            throw new MensajeExisteExcepcion("El carrito de compra no existe.");
        }
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public CarritoCompra obtenerCarritoPorId(Integer id) {
        return this.carritoCompraRepository.seleccionarPorId(id);
    }



}
