package com.software.kefa.repository;

import java.util.List;

import com.software.kefa.repository.modelo.CategoriaProducto;

public interface ICategoriaProductoRepository {
    public List<CategoriaProducto> seleccionarTodo();
    public void insertar(CategoriaProducto producto);
    public void actualizar(CategoriaProducto producto);
}
