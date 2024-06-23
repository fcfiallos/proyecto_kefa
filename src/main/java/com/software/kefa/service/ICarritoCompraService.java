package com.software.kefa.service;

import com.software.kefa.repository.modelo.CarritoCompra;

public interface ICarritoCompraService {
    public void guardar(CarritoCompra carritoCompra);
    public void actualizar(CarritoCompra carritoCompra);
}
