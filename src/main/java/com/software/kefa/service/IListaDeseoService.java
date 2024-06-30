package com.software.kefa.service;

import java.util.List;

import com.software.kefa.repository.modelo.ListaDeseos;
import com.software.kefa.repository.modelo.Producto;

public interface IListaDeseoService {
    public void guardar(ListaDeseos listaDeseo, String nickname, Integer productoId);
    public void actualizar(ListaDeseos listaDeseo);
    public List<Producto> buscarTodo(Integer id);
    public ListaDeseos buscarPorId(Integer id);
    public void agregarProductoALaLista(Integer listaDeseoId, Integer productoId);
}
