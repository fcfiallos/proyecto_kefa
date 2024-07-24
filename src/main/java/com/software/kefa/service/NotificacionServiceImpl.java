package com.software.kefa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.INotificacionRepository;
import com.software.kefa.repository.modelo.Notificacion;

import jakarta.transaction.Transactional;

/**
 * This class implements the INotificacionService interface and provides
 * the implementation for the methods defined in the interface.
 */
@Service
public class NotificacionServiceImpl implements INotificacionService {
    @Autowired
    private INotificacionRepository notificacionRepository;

    /**
     * Saves the given notification.
     *
     * @param notificacion The notification to be saved.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(Notificacion notificacion) {
        this.notificacionRepository.insertar(notificacion);
    }

    /**
     * Updates the given notification.
     *
     * @param notificacion The notification to be updated.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(Notificacion notificacion) {
        this.notificacionRepository.actualizar(notificacion);
    }

    /**
     * Retrieves a notification by its ID.
     *
     * @param id The ID of the notification to be retrieved.
     * @return The notification with the specified ID, or null if not found.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Notificacion buscarPorId(Integer id) {
        return this.notificacionRepository.seleccionarPorId(id);
    }

    /**
     * Retrieves all notifications for a given nickname.
     *
     * @param nickname The nickname for which to retrieve the notifications.
     * @return A list of notifications for the specified nickname.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<Notificacion> buscarTodoPorNickname(String nickname) {
        return this.notificacionRepository.seleccionarTodoPorNickname(nickname);
    }

}
