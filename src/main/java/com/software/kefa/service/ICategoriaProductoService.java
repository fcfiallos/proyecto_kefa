package com.software.kefa.service;

import java.util.List;

import com.software.kefa.repository.modelo.CategoriaProducto;
import com.software.kefa.repository.modelo.modelosdto.CategoriaProductoDTO;

public interface ICategoriaProductoService {
    public List<CategoriaProductoDTO> buscarTodo();

    public void guardar(CategoriaProducto categoriaProducto);

    public void actualizar(CategoriaProducto categoriaProducto);
}
