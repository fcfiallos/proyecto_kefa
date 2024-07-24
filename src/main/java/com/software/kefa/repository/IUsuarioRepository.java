package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.repository.modelo.modelosdto.UsuarioPerfilDTO;

/**
 * This interface represents a repository for managing user data.
 */
public interface IUsuarioRepository {
    
    /**
     * Inserts a new user into the repository.
     * 
     * @param usuario The user to be inserted.
     */
    public void insertar(Usuario usuario);
    
    /**
     * Updates an existing user in the repository.
     * 
     * @param usuario The user to be updated.
     */
    public void actualizar (Usuario usuario);
    
    /**
     * Retrieves a user from the repository based on their cedula (identification number).
     * 
     * @param cedula The cedula of the user to be retrieved.
     * @return The user with the specified cedula, or null if not found.
     */
    public Usuario seleccionarPorCedula (String cedula);
    
    /**
     * Retrieves a user from the repository based on their nickname.
     * 
     * @param nickname The nickname of the user to be retrieved.
     * @return The user with the specified nickname, or null if not found.
     */
    public Usuario seleccionarPorNickname (String nickname);
    
    /**
     * Retrieves a user from the repository based on their password.
     * 
     * @param contrasenia The password of the user to be retrieved.
     * @return The user with the specified password, or null if not found.
     */
    public Usuario seleccionarPorContrasenia (String contrasenia);
    
    /**
     * Retrieves a user from the repository based on their email.
     * 
     * @param email The email of the user to be retrieved.
     * @return The user with the specified email, or null if not found.
     */
    public Usuario seleccionarPorEmail (String email);
    
    /**
     * Retrieves additional information about a user from the repository based on their nickname.
     * 
     * @param nickname The nickname of the user to retrieve additional information for.
     * @return The additional information about the user, or null if not found.
     */
    public UsuarioPerfilDTO seleccionarInformacion (String nickname);
}
