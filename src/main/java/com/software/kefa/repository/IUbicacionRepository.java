package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Ubicacion;

/**
 * This interface represents a repository for managing Ubicacion objects.
 */
public interface IUbicacionRepository {
    /**
     * Inserts a new Ubicacion object into the repository.
     *
     * @param ubicacion The Ubicacion object to be inserted.
     */
    public void insertar(Ubicacion ubicacion);
}