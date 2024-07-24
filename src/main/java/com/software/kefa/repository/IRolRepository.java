package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Rol;

/**
 * The IRolRepository interface represents a repository for managing roles.
 */
public interface IRolRepository {
    
    /**
     * Inserts a new role into the repository.
     *
     * @param rol The role to be inserted.
     */
    public void insertar(Rol rol);
    
    /**
     * Retrieves a role from the repository based on the given nickname.
     *
     * @param nickname The nickname of the role to be retrieved.
     * @return The role with the specified nickname, or null if not found.
     */
    public Rol seleccionarPorNickname(String nickname);
}
