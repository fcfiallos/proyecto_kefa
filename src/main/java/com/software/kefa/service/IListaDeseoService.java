package com.software.kefa.service;

import com.software.kefa.repository.modelo.ListaDeseos;
import com.software.kefa.repository.modelo.Producto;

public interface IListaDeseoService {
    void agregarProducto(Integer listaDeseosId, Producto producto, String nickname);

    void eliminarProducto(Integer listaDeseosId, Producto producto);

    ListaDeseos obtenerListaDeseosPorId(Integer id);

}
