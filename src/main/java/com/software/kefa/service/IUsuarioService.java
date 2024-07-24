package com.software.kefa.service;

import java.util.List;

import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.repository.modelo.modelosdto.UsuarioPerfilDTO;
import com.software.kefa.service.modelosto.UsuarioRegistroTO;

/**
 * This interface represents the service layer for managing user-related operations.
 */
public interface IUsuarioService {
    
    /**
     * Saves a new user.
     * 
     * @param usuarioTO The user registration data.
     */
    public void guardar(UsuarioRegistroTO usuarioTO);
    
    /**
     * Updates an existing user.
     * 
     * @param usuario The user to be updated.
     */
    public void actualizar (Usuario usuario);
    
    /**
     * Checks if a user with the given cedula exists.
     * 
     * @param cedula The cedula to check.
     * @return true if a user with the given cedula exists, false otherwise.
     */
    public boolean existeUsuarioCedula (String cedula);
    
    /**
     * Checks if a user with the given nickname exists.
     * 
     * @param nickname The nickname to check.
     * @return true if a user with the given nickname exists, false otherwise.
     */
    public boolean existeUsuarioNickname(String nickname);
    
    /**
     * Searches for a user with the given nickname.
     * 
     * @param nickname The nickname to search for.
     * @return The user with the given nickname, or null if not found.
     */
    public Usuario buscarPorNickname (String nickname);
    
    /**
     * Performs efficient registration of multiple users.
     * 
     * @param usuarioEfi The list of user registration data.
     */
    public void registroEficiente(List<UsuarioRegistroTO> usuarioEfi);
    
    /**
     * Retrieves user profile information for the given nickname.
     * 
     * @param nickname The nickname of the user.
     * @return The user profile information for the given nickname, or null if not found.
     */
    public UsuarioPerfilDTO buscarInformacion(String nickname);
    
    /**
     * Performs user login with the given nickname and password.
     * 
     * @param nickname The nickname of the user.
     * @param contrasenia The password of the user.
     */
    public void iniciarSesion(String nickname, String contrasenia);
    
    /**
     * Performs user logout for the given nickname.
     * 
     * @param nickname The nickname of the user.
     */
    public void cerrarSesion(String nickname);
    
    /**
     * Searches for a user with the given email.
     * 
     * @param email The email to search for.
     * @return The user with the given email, or null if not found.
     */
    public Usuario buscarPorEmail (String email);
    
    /**
     * Validates the recovery of a forgotten password for the given email and user registration data.
     * 
     * @param email The email of the user.
     * @param usuarioTO The user registration data.
     * @return true if the recovery is valid, false otherwise.
     */
    public boolean validarRecuperarContrasenia(String email, UsuarioRegistroTO usuarioTO);
    
    /**
     * Performs the recovery of a forgotten password for the given email and user registration data.
     * 
     * @param email The email of the user.
     * @param usuarioTO The user registration data.
     */
    public void recuperarContrasenia(String email, UsuarioRegistroTO usuarioTO);
}
