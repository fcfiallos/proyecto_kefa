package com.software.kefa.service;

import java.util.List;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Producto;

public interface ICarritoCompraService {
    
    public void guardar(CarritoCompra carritoCompra);

    public void actualizar(CarritoCompra carritoCompra, Integer detalleId);

    public CarritoCompra buscarPorId(Integer id);

    public List<Producto> buscarTodo(Integer idCarritoCompra);

    public CarritoCompra agregarProductoAlCarrito(Integer productoId, String nickname, Integer cantidad, CarritoCompra carritoCompra);

    public void eliminar(CarritoCompra carritoCompra, Integer detalleId);

    public List<CarritoCompra> buscarPorNickname(String nickname);
}
