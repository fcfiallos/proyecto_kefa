package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Envio;

/**
 * The interface IEnvioRepository represents a repository for managing Envio objects.
 */
public interface IEnvioRepository {
    
    /**
     * Inserts a new Envio object into the repository.
     *
     * @param envio The Envio object to be inserted.
     */
    public void insertar(Envio envio);
    
    /**
     * Updates an existing Envio object in the repository.
     *
     * @param envio The Envio object to be updated.
     */
    public void actualizar(Envio envio);
    
    /**
     * Deletes an existing Envio object from the repository.
     *
     * @param envio The Envio object to be deleted.
     */
    public void eliminar(Envio envio);
    
    /**
     * Retrieves an Envio object from the repository based on its ID.
     *
     * @param id The ID of the Envio object to be retrieved.
     * @return The retrieved Envio object, or null if not found.
     */
    public Envio seleccionarPorId(Integer id);
}

