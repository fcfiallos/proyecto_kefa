package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.ICarritoCompraRepository;
import com.software.kefa.repository.IProductoRepository;
import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Producto;

import jakarta.transaction.Transactional;

@Service
public class CarritoComprasServiceImpl implements ICarritoCompraService {

    @Autowired
    private ICarritoCompraRepository carritoCompraRepository;

    @Autowired
    private IProductoRepository productoRepository;

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
    public void agregarProductoAlCarrito(Integer carritoCompraId, Integer productoId) {
        CarritoCompra carritoCompra = carritoCompraRepository.seleccionarPorId(carritoCompraId);
        carritoCompra.setFechaSeleccionada(LocalDateTime.now());

        Producto producto = productoRepository.seleccionarPorId(productoId);
        if (carritoCompra != null && producto != null) {
            carritoCompra.getProductos().add(producto);
            carritoCompraRepository.actualizar(carritoCompra);
        } else {
            throw new RuntimeException("Carrito de Compras o Producto no encontrado");
        }
    }

}
