package com.software.kefa.repository;

import java.util.List;

import com.software.kefa.repository.modelo.CategoriaProducto;
import com.software.kefa.repository.modelo.Producto;

public interface ICategoriaProductoRepository {
    public List<CategoriaProducto> seleccionarTodo();
    public void insertar(CategoriaProducto producto);
    public void actualizar(CategoriaProducto producto);
    public CategoriaProducto seleccionarPorId(Integer id);
    public List<Producto> seleccionarProductosPorCategoria(Integer categoriaId);
}
