package com.software.kefa.repository;

import java.util.List;
 
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.repository.modelo.modelosdto.ProductoDTO;
 
public interface IProductoRepository {
    public List<ProductoDTO> seleccionarTodo();
    public void insertar(Producto producto);
    public void actualizar(Producto producto);
}