package com.software.kefa.repository;

import java.util.List;

import com.software.kefa.repository.modelo.Producto;

/**
 * This interface represents a repository for managing Producto objects.
 */
public interface IProductoRepository {
    
    /**
     * Retrieves a list of Producto objects based on the given categoriaID.
     *
     * @param categoriaID the ID of the categoria
     * @return a list of Producto objects
     */
    public List<Producto> seleccionarPorCategoriaId(Integer categoriaID);

    /**
     * Retrieves a list of all Producto objects.
     *
     * @return a list of all Producto objects
     */
    public List<Producto> seleccionarTodo();

    /**
     * Inserts a new Producto object into the repository.
     *
     * @param producto the Producto object to insert
     */
    public void insertar(Producto producto);

    /**
     * Updates an existing Producto object in the repository.
     *
     * @param producto the Producto object to update
     */
    public void actualizar(Producto producto);

    /**
     * Retrieves a Producto object based on the given codigo.
     *
     * @param codigo the codigo of the Producto
     * @return the Producto object
     */
    public Producto seleccionarPorCodigo(String codigo);

    /**
     * Retrieves a Producto object based on the given id.
     *
     * @param id the id of the Producto
     * @return the Producto object
     */
    public Producto seleccionarPorId(Integer id);

    /**
     * Retrieves the quantity of products based on the given productoId.
     *
     * @param productoId the ID of the producto
     * @return the quantity of products
     */
    public Integer seleccionarPorCantidadProductos(Integer productoId);

}