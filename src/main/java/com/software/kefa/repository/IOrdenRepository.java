package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Orden;

/**
 * This interface represents a repository for managing orders.
 */
public interface IOrdenRepository {
    
    /**
     * Inserts a new order into the repository.
     * 
     * @param orden The order to be inserted.
     */
    public void insertar(Orden orden);
    
    /**
     * Updates an existing order in the repository.
     * 
     * @param orden The order to be updated.
     */
    public void actualizar(Orden orden);
    
    /**
     * Retrieves an order from the repository based on its code.
     * 
     * @param codigo The code of the order to be retrieved.
     * @return The order with the specified code, or null if not found.
     */
    public Orden seleccionarPorCodigo(String codigo);
    
    /**
     * Retrieves an order from the repository based on its ID.
     * 
     * @param id The ID of the order to be retrieved.
     * @return The order with the specified ID, or null if not found.
     */
    public Orden seleccionarPorId(Integer id);
    
    /**
     * Retrieves all orders from the repository.
     * 
     * @return A list of all orders in the repository.
     */
    public Orden seleccionarTodo();
}
