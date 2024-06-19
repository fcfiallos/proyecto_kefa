package com.software.kefa.repository;

import java.util.List;

import com.software.kefa.repository.modelo.ListaDeseos;

public interface IListaDeseoRepository {
    public void insertar(ListaDeseos listaDeseo);
    public void actualizar(ListaDeseos listaDeseo);
    public List<ListaDeseos> seleccionarTodo();
}
