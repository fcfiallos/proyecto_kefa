package com.software.kefa.repository;

import java.util.List;

import com.software.kefa.repository.modelo.CategoriaProducto;
import com.software.kefa.repository.modelo.modelosdto.CategoriaProductoDTO;


public interface ICategoriaProductoRepository {
    public List<CategoriaProductoDTO> seleccionarTodo();

    public void insertar(CategoriaProducto categoriaProducto);

    public void actualizar(CategoriaProducto categoriaProducto);
}
