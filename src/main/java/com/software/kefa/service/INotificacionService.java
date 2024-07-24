package com.software.kefa.service;

import java.util.List;

import com.software.kefa.repository.modelo.Notificacion;

/**
 * This interface represents the contract for a NotificacionService.
 * It defines methods for saving, updating, and searching for notifications.
 */
public interface INotificacionService {
    
    /**
     * Saves a notification.
     * 
     * @param notificacion The notification to be saved.
     */
    public void guardar(Notificacion notificacion);
    
    /**
     * Updates a notification.
     * 
     * @param notificacion The notification to be updated.
     */
    public void actualizar(Notificacion notificacion);
    
    /**
     * Searches for a notification by its ID.
     * 
     * @param id The ID of the notification to search for.
     * @return The found notification, or null if not found.
     */
    public Notificacion buscarPorId(Integer id);

    /**
     * Searches for all notifications associated with a given nickname.
     * 
     * @param nickname The nickname to search for.
     * @return A list of notifications associated with the given nickname.
     */
    public List<Notificacion> buscarTodoPorNickname(String nickname);
}

