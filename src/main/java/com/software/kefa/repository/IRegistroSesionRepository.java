package com.software.kefa.repository;

import com.software.kefa.repository.modelo.RegistroSesion;

/**
 * This interface represents a repository for managing RegistroSesion objects.
 */
public interface IRegistroSesionRepository {
    
    /**
     * Inserts a new RegistroSesion object into the repository.
     * 
     * @param registroSesion The RegistroSesion object to insert.
     */
    public void insertar(RegistroSesion registroSesion);
    
    /**
     * Updates an existing RegistroSesion object in the repository.
     * 
     * @param registroSesion The RegistroSesion object to update.
     */
    public void actualizar(RegistroSesion registroSesion);
    
    /**
     * Retrieves a RegistroSesion object from the repository based on its ID.
     * 
     * @param id The ID of the RegistroSesion object to retrieve.
     * @return The retrieved RegistroSesion object, or null if not found.
     */
    public RegistroSesion seleccionarPorId(Integer id);
    
    /**
     * Retrieves a RegistroSesion object from the repository based on its nickname.
     * 
     * @param nickname The nickname of the RegistroSesion object to retrieve.
     * @return The retrieved RegistroSesion object, or null if not found.
     */
    public RegistroSesion seleccionarPorNickname(String nickname);
}
