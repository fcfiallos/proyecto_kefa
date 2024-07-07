package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Pago;

public interface IMetodoPagoRepository {
    public void insertar (Pago pago);
    public void actualizar (Pago pago);
    public void eliminar (Pago pago);
    public Pago seleccionarPorId (Integer id);
}
