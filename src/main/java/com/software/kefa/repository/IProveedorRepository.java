package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Proveedor;
 
/**
 * This interface represents a repository for managing Proveedor objects.
 */
public interface IProveedorRepository {

    /**
     * Retrieves a Proveedor object based on the given nombre.
     *
     * @param nombre The nombre of the Proveedor to retrieve.
     * @return The Proveedor object matching the given nombre, or null if not found.
     */
    public Proveedor seleccionarPorNombre(String nombre);

    /**
     * Inserts a new Proveedor object into the repository.
     *
     * @param proveedor The Proveedor object to insert.
     */
    public void insertar(Proveedor proveedor);

    /**
     * Updates an existing Proveedor object in the repository.
     *
     * @param proveedor The Proveedor object to update.
     */
    public void actualizar(Proveedor proveedor);
}