package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Factura;

public interface IFacturaRepository {
    public void insertar (Factura factura);
    public void actualizar (Factura factura);
    public void eliminar (Factura factura);
    public Factura seleccionarPorId (Integer id);
    public Factura seleccionarTodo();
}
