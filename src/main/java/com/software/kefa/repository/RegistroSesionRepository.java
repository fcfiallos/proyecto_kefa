package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.RegistroSesion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class RegistroSesionRepository implements IRegistroSesionRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(RegistroSesion registroSesion) {
        this.entityManager.persist(registroSesion);
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(RegistroSesion registroSesion) {
        this.entityManager.merge(registroSesion);
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public RegistroSesion seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(RegistroSesion.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public RegistroSesion seleccionarPorNickname(String nickname) {
        try {
            return this.entityManager.createQuery("SELECT r FROM RegistroSesion r WHERE r.nickname = :nickname", RegistroSesion.class)
                    .setParameter("nickname", nickname)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        
    }

}
