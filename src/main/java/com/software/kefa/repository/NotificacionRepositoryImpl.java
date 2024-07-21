package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Notificacion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class NotificacionRepositoryImpl implements INotificacionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(Notificacion notificacion) {
        this.entityManager.persist(notificacion);
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(Notificacion notificacion) {
        this.entityManager.merge(notificacion);
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void eliminar(Notificacion notificacion) {
        this.entityManager.remove(notificacion);
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Notificacion seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(Notificacion.class, id);
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public List<Notificacion> seleccionarTodoPorNickname(String nickname) {
        return this.entityManager.createQuery("SELECT n FROM Notificacion n WHERE n.usuario.nickname = :nickname",
                Notificacion.class).setParameter("nickname", nickname).getResultList();
    }

}
