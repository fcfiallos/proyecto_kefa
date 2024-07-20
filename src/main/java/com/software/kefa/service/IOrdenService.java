package com.software.kefa.service;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Orden;

public interface IOrdenService {
    public void guardar(Orden orden);
    public void actualizar(Orden orden);
    public Orden buscarPorId(Integer id);
    public Orden crearOrdenDePago(String nickname, CarritoCompra carrito);
}
