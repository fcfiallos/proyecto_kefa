package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Orden;

public interface IOrdenRepository {
    public void insertar(Orden orden);
    public void actualizar(Orden orden);
    public Orden seleccionarPorCodigo(String codigo);
    public Orden seleccionarPorId(Integer id);
    public Orden seleccionarTodo();
}
