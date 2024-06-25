package com.software.kefa.repository;

import java.util.Set;

import com.software.kefa.repository.modelo.ListaDeseos;
import com.software.kefa.repository.modelo.Producto;

public interface IListaDeseoRepository {
    public void insertar(ListaDeseos listaDeseo);
    public void actualizar(ListaDeseos listaDeseo);
    public Set<Producto> seleccionarTodo(Integer id);
    public ListaDeseos seleccionarPorId(Integer id);
}
