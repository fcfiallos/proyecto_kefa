package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Notificacion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class NotificacionRepositoryImpl implements INotificacionRepository{
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void insertar(Notificacion notificacion) {
        this.entityManager.persist(notificacion);
    }

    @Override
    public void actualizar(Notificacion notificacion) {
        this.entityManager.merge(notificacion);
    }

    @Override
    public void eliminar(Notificacion notificacion) {
        this.entityManager.remove(notificacion);
    }

    @Override
    public Notificacion seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(Notificacion.class, id); 
        } catch (NoResultException e) {
            return null;
        }
       
    }

}
