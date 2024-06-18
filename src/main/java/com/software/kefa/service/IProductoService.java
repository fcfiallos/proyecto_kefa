package com.software.kefa.service;

import java.util.List;
 
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.repository.modelo.modelosdto.ProductoDTO;
import com.software.kefa.service.modelosto.ProductoTO;
 
public interface IProductoService {
    public List<Producto> buscarTodo();
    public void guardar(ProductoTO producto);
    public void actualizar(Producto producto);
    public boolean existeProductoCodigo (String codigo);
    public boolean existeProveedorNombre(String nombre);
    public Producto buscarPorCodigo(String codigo);
}