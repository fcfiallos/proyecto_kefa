package com.software.kefa.repository;

import java.util.List;

import com.software.kefa.repository.modelo.Notificacion;

/**
 * This interface represents a repository for managing notifications.
 */
public interface INotificacionRepository {
    
    /**
     * Inserts a new notification into the repository.
     * 
     * @param notificacion The notification to be inserted.
     */
    public void insertar(Notificacion notificacion);
    
    /**
     * Updates an existing notification in the repository.
     * 
     * @param notificacion The notification to be updated.
     */
    public void actualizar(Notificacion notificacion);
    
    /**
     * Deletes a notification from the repository.
     * 
     * @param notificacion The notification to be deleted.
     */
    public void eliminar(Notificacion notificacion);
    
    /**
     * Retrieves a notification from the repository based on its ID.
     * 
     * @param id The ID of the notification to be retrieved.
     * @return The retrieved notification, or null if not found.
     */
    public Notificacion seleccionarPorId(Integer id);
    
    /**
     * Retrieves all notifications from the repository based on a given nickname.
     * 
     * @param nickname The nickname used to filter the notifications.
     * @return A list of notifications matching the given nickname.
     */
    public List<Notificacion> seleccionarTodoPorNickname(String nickname);
}

