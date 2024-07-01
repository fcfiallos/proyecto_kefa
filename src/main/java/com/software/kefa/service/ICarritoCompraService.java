package com.software.kefa.service;

import java.util.List;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Producto;

public interface ICarritoCompraService {
    public void guardar(CarritoCompra carritoCompra, String nickname, Integer productoId, Integer cantidad);
    public void actualizar(CarritoCompra carritoCompra);
    public CarritoCompra buscarPorId(Integer id);
    public List<Producto> buscarTodo(Integer idCarritoCompra);
    public void agregarProductoAlCarrito(Integer carritoCompraId, Integer productoId, String nickname, Integer cantidad);
    public void eliminarProductoDelCarrito(Integer carritoCompraId, Integer productoId);
}
