package com.software.kefa.service;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Producto;

public interface ICarritoCompraService {
    void agregarProducto(Integer carritoId, Producto producto, String nickname);

    void eliminarProducto(Integer carritoId, Producto producto);

    CarritoCompra obtenerCarritoPorId(Integer id);

}
