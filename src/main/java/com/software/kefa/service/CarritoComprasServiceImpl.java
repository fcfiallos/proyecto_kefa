package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.ICarritoCompraRepository;
import com.software.kefa.repository.IProductoRepository;
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.repository.modelo.Usuario;

import jakarta.transaction.Transactional;

@Service
public class CarritoComprasServiceImpl implements ICarritoCompraService {

    @Autowired
    private ICarritoCompraRepository carritoCompraRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(CarritoCompra carritoCompra) {
        this.carritoCompraRepository.insertar(carritoCompra);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(CarritoCompra carritoCompra) {
        this.carritoCompraRepository.actualizar(carritoCompra);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public CarritoCompra buscarPorId(Integer id) {
        return this.carritoCompraRepository.seleccionarPorId(id);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Set<Producto> buscarTodo() {
        return this.carritoCompraRepository.seleccionarTodo();
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void agregarProductoAlCarrito(Integer carritoCompraId, Integer productoId, String nickname) {
        Usuario usuario = this.iUsuarioRepository.seleccionarPorNickname(nickname);
        CarritoCompra carritoCompra = carritoCompraRepository.seleccionarPorId(carritoCompraId);
        carritoCompra.setFechaSeleccionada(LocalDateTime.now());

        Producto producto = productoRepository.seleccionarPorId(productoId);
        if (carritoCompra != null && producto != null) {
            carritoCompra.getProductos().add(producto);
            carritoCompra.setProductos(this.buscarTodo());
            carritoCompra.setUsuario(usuario);
            carritoCompraRepository.actualizar(carritoCompra);
        } else {
            throw new RuntimeException("Carrito de Compras o Producto no encontrado");
        }
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void eliminarProductoDelCarrito(Integer carritoCompraId, Integer productoId) {
        CarritoCompra carritoCompra = carritoCompraRepository.seleccionarPorId(carritoCompraId);
        if (carritoCompra == null) {
            throw new RuntimeException("Carrito de Compras no encontrado");
        }

        Producto producto = productoRepository.seleccionarPorId(productoId);
        if (producto == null) {
            throw new RuntimeException("Producto no encontrado");
        }

        boolean productoEliminado = carritoCompra.getProductos().remove(producto);
        if (!productoEliminado) {
            throw new RuntimeException("El producto no se encontraba en el carrito de compras");
        }

        carritoCompraRepository.actualizar(carritoCompra);
    }

}
