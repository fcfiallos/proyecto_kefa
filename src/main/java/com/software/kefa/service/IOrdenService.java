package com.software.kefa.service;

import java.util.List;

import com.software.kefa.repository.modelo.Orden;
import com.software.kefa.repository.modelo.modelosdto.OrdenDTO;

public interface IOrdenService {
    public void guardar(Orden orden);
    public void actualizar(Orden orden);
    public Orden buscarPorId(Integer id);
    public Orden buscarTodo();
    public Orden buscarPorCodigo(String codigo);
    public List<OrdenDTO> buscarTodos();
    public Orden crearOrdenDePago(String nickname, Integer carritoId);
}
