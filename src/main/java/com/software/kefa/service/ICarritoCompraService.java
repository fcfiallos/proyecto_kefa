package com.software.kefa.service;

import java.util.Set;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Producto;

public interface ICarritoCompraService {
    public void guardar(CarritoCompra carritoCompra);
    public void actualizar(CarritoCompra carritoCompra);
    public CarritoCompra buscarPorId(Integer id);
    public Set<Producto> buscarTodo();
    public void agregarProductoAlCarrito(Integer carritoCompraId, Integer productoId);
}
