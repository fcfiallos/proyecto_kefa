package com.software.kefa.repository;

import java.util.List;
 
import com.software.kefa.repository.modelo.Producto;
 
public interface IProductoRepository {
    public List<Producto> seleccionarPorCategoriaId(Integer categoriaID);
    public void insertar(Producto producto);
    public void actualizar(Producto producto);
    public Producto seleccionarPorCodigo(String codigo);
    public Producto seleccionarPorId(Integer id);
}