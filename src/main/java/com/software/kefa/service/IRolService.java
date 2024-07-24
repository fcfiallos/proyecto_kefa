package com.software.kefa.service;

import com.software.kefa.repository.modelo.Rol;

/**
 * This interface represents the service for managing roles.
 */
public interface IRolService {
    
    /**
     * Searches for a role by its nickname.
     * 
     * @param nickname the nickname of the role to search for
     * @return the role with the specified nickname, or null if not found
     */
    public Rol buscarPorNickname(String nickname);
}
