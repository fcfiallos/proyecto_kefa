package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Notificacion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * Implementation of the INotificacionRepository interface that provides
 * methods for inserting, updating, deleting, and selecting notifications
 * from the database.
 */
@Repository
@Transactional
public class NotificacionRepositoryImpl implements INotificacionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Inserts a new notification into the database.
     *
     * @param notificacion The notification to be inserted.
     */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(Notificacion notificacion) {
        this.entityManager.persist(notificacion);
    }

    /**
     * Updates an existing notification in the database.
     *
     * @param notificacion The notification to be updated.
     */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(Notificacion notificacion) {
        this.entityManager.merge(notificacion);
    }

    /**
     * Deletes a notification from the database.
     *
     * @param notificacion The notification to be deleted.
     */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void eliminar(Notificacion notificacion) {
        this.entityManager.remove(notificacion);
    }

    /**
     * Retrieves a notification from the database based on its ID.
     *
     * @param id The ID of the notification to be retrieved.
     * @return The retrieved notification, or null if not found.
     */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Notificacion seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(Notificacion.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Retrieves all notifications from the database for a given nickname.
     *
     * @param nickname The nickname of the user.
     * @return A list of notifications for the given nickname.
     */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public List<Notificacion> seleccionarTodoPorNickname(String nickname) {
        return this.entityManager.createQuery("SELECT n FROM Notificacion n WHERE n.usuario.nickname = :nickname",
                Notificacion.class).setParameter("nickname", nickname).getResultList();
    }
}
