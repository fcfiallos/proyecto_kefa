package com.software.kefa.repository;

import com.software.kefa.repository.modelo.DetalleOrden;

public interface IDetalleOrdenRepository {
    public DetalleOrden seleccionarPorId(Integer id);
    public void insertar(DetalleOrden detalleOrden);
    public void actualizar(DetalleOrden detalleOrden);
}
