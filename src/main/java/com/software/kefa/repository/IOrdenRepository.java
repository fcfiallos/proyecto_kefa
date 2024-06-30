package com.software.kefa.repository;

import java.util.List;

import com.software.kefa.repository.modelo.Orden;
import com.software.kefa.repository.modelo.modelosdto.OrdenDTO;

public interface IOrdenRepository {
    public void insertar(Orden orden);
    public void actualizar(Orden orden);
    public Orden seleccionarPorCodigo(String codigo);
    public Orden seleccionarPorId(Integer id);
    public Orden seleccionarTodo();
    public List<OrdenDTO> seleccionarTodos();
}
