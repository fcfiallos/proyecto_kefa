package com.software.kefa.repository;


import java.util.List;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.DetalleOrden;
import com.software.kefa.repository.modelo.Producto;

public interface ICarritoCompraRepository {
    public void insertar(CarritoCompra carritoCompra);
    public void actualizar(CarritoCompra carritoCompra);
    public CarritoCompra seleccionarPorId(Integer id);
    public List<Producto> seleccionarTodo(Integer id);
    public CarritoCompra seleccionarPorUsuarioNickname(String nickname);
    public List<DetalleOrden> seleccionarDetalleOrdenPorCarritoCompraId(Integer id);
    public void eliminar(Integer id);
}
