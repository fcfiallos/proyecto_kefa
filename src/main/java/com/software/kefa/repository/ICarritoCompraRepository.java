package com.software.kefa.repository;


import java.util.Set;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Producto;

public interface ICarritoCompraRepository {
    public void insertar(CarritoCompra carritoCompra);
    public void actualizar(CarritoCompra carritoCompra);
    public CarritoCompra seleccionarPorId(Integer id);
    public Set<Producto> seleccionarTodo();
}
