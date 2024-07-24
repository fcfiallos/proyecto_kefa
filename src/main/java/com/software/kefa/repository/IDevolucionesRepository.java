package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Devoluciones;

/**
 * This interface represents a repository for managing Devoluciones objects.
 */
public interface IDevolucionesRepository {
    /**
     * Inserts a Devoluciones object into the repository.
     *
     * @param devolucion The Devoluciones object to be inserted.
     */
    public void insertar(Devoluciones devolucion);
}
