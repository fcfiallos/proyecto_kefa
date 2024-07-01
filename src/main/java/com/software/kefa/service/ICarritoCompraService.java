package com.software.kefa.service;

import java.util.List;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.DetalleOrden;
import com.software.kefa.repository.modelo.Producto;

public interface ICarritoCompraService {
    // public void guardar(CarritoCompra carritoCompra, String nickname, Integer
    // productoId, Integer cantidad);
    public void guardar(CarritoCompra carritoCompra);

    public void actualizar(CarritoCompra carritoCompra);

    public CarritoCompra buscarPorId(Integer id);

    public List<Producto> buscarTodo(Integer idCarritoCompra);

    public List<DetalleOrden> buscarDetallePorIdCarrito(Integer idCarritoCompra);

    public CarritoCompra buscarPorNickname(String nickname);

    public CarritoCompra agregarProductoAlCarrito(Integer productoId, String nickname, Integer cantidad, CarritoCompra carritoCompra);

    public void eliminarProductoDelCarrito(Integer detalleOrdenId, String nickname);
}
