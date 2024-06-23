package com.software.kefa.service;


import java.util.List;

import com.software.kefa.repository.modelo.CategoriaProducto;
import com.software.kefa.repository.modelo.Producto;

public interface ICategoriaProductoService {
    public List<CategoriaProducto> buscarTodo();
    public void guardar(CategoriaProducto categoriaProducto);
    public void actualizar(CategoriaProducto categoriaProducto);
    public CategoriaProducto buscarPorId(Integer id);
    public List<Producto> buscarProductosPorCategoria(Integer categoriaId);
}
