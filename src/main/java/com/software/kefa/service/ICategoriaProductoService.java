package com.software.kefa.service;


import java.util.List;

import com.software.kefa.repository.modelo.CategoriaProducto;

/**
 * This interface represents the service for managing CategoriaProducto objects.
 */
public interface ICategoriaProductoService {
    
    /**
     * Retrieves all CategoriaProducto objects.
     *
     * @return a list of all CategoriaProducto objects
     */
    public List<CategoriaProducto> buscarTodo();
    
    /**
     * Saves a new CategoriaProducto object.
     *
     * @param categoriaProducto the CategoriaProducto object to be saved
     */
    public void guardar(CategoriaProducto categoriaProducto);
    
    /**
     * Updates an existing CategoriaProducto object.
     *
     * @param categoriaProducto the CategoriaProducto object to be updated
     */
    public void actualizar(CategoriaProducto categoriaProducto);
    
    /**
     * Retrieves a CategoriaProducto object by its ID.
     *
     * @param id the ID of the CategoriaProducto object to be retrieved
     * @return the CategoriaProducto object with the specified ID, or null if not found
     */
    public CategoriaProducto buscarPorId(Integer id);
}
