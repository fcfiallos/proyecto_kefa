package com.software.kefa.service;

import java.util.List;

import com.software.kefa.repository.modelo.ListaDeseos;

public interface IListaDeseoService {
    public void guardar(ListaDeseos listaDeseo);
    public void actualizar(ListaDeseos listaDeseo);
    public List<ListaDeseos> buscarTodo();
}
