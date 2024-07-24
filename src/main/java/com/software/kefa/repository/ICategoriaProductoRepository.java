package com.software.kefa.repository;

import java.util.List;

import com.software.kefa.repository.modelo.CategoriaProducto;

/**
 * This interface represents a repository for managing CategoriaProducto objects.
 */
public interface ICategoriaProductoRepository {

    /**
     * Retrieves all CategoriaProducto objects from the repository.
     *
     * @return a list of all CategoriaProducto objects
     */
    public List<CategoriaProducto> seleccionarTodo();

    /**
     * Inserts a new CategoriaProducto object into the repository.
     *
     * @param producto the CategoriaProducto object to insert
     */
    public void insertar(CategoriaProducto producto);

    /**
     * Updates an existing CategoriaProducto object in the repository.
     *
     * @param producto the CategoriaProducto object to update
     */
    public void actualizar(CategoriaProducto producto);

    /**
     * Retrieves a CategoriaProducto object from the repository based on its ID.
     *
     * @param id the ID of the CategoriaProducto object to retrieve
     * @return the CategoriaProducto object with the specified ID, or null if not found
     */
    public CategoriaProducto seleccionarPorId(Integer id);
}
