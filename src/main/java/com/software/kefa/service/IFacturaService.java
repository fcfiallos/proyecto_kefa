package com.software.kefa.service;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Factura;
import com.software.kefa.repository.modelo.Orden;

public interface IFacturaService {
    public Factura buscarPorId(Integer id);
    public void guardar(Factura factura);
    public void actualizar(Factura factura);
    public Factura enviarFactura(CarritoCompra carritoCompra, String nickname, Orden orden);
}
