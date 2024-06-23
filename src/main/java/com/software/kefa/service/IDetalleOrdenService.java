package com.software.kefa.service;

import com.software.kefa.repository.modelo.DetalleOrden;

public interface IDetalleOrdenService {
    public void guardar(DetalleOrden detalleOrden);
    public void actualizar(DetalleOrden detalleOrden);
}
