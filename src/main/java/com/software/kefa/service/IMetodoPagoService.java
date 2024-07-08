package com.software.kefa.service;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Pago;
import com.software.kefa.service.modelosto.MetodoPagoTO;

public interface IMetodoPagoService {
    public void guardar (Pago pago, MetodoPagoTO metodoPagoTO, String nickname, CarritoCompra carritoCompra);
    public void eliminar (Pago pago);
    public Pago buscarPorId (Integer id);
    public void actualizar (Pago pago);
}
