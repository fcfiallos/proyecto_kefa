package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Factura;

/**
 * The IFacturaRepository interface represents a repository for managing Factura objects.
 */
public interface IFacturaRepository {
    
    /**
     * Inserts a new Factura object into the repository.
     *
     * @param factura The Factura object to insert.
     */
    public void insertar(Factura factura);
    
    /**
     * Updates an existing Factura object in the repository.
     *
     * @param factura The Factura object to update.
     */
    public void actualizar(Factura factura);
    
    /**
     * Deletes a Factura object from the repository.
     *
     * @param factura The Factura object to delete.
     */
    public void eliminar(Factura factura);
    
    /**
     * Retrieves a Factura object from the repository based on its ID.
     *
     * @param id The ID of the Factura object to retrieve.
     * @return The retrieved Factura object, or null if not found.
     */
    public Factura seleccionarPorId(Integer id);
    
    /**
     * Retrieves all Factura objects from the repository.
     *
     * @return A Factura object representing all the retrieved Factura objects.
     */
    public Factura seleccionarTodo();
}

