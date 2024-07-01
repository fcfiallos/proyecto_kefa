package com.software.kefa.repository;

import java.util.List;

import com.software.kefa.repository.modelo.DetalleOrden;
import com.software.kefa.repository.modelo.ListaDeseos;
import com.software.kefa.repository.modelo.Producto;

public interface IListaDeseoRepository {
    public void insertar(ListaDeseos listaDeseo);
    public void actualizar(ListaDeseos listaDeseo);
    public List<Producto> seleccionarTodo(Integer id);
    public ListaDeseos seleccionarPorId(Integer id);
    public ListaDeseos seleccionarPorUsuarioNickname(String nickname);
    public void eliminarProductoDeLista(Integer detalleId, String nickname);
    public List<DetalleOrden> seleccionarDetalleOrdenPorListaDeseoId(Integer id);
}
