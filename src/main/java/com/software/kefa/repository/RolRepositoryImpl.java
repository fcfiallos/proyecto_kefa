package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Rol;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Repository
@Transactional
public class RolRepositoryImpl implements IRolRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void insertar(Rol rol) {
        this.entityManager.persist(rol);
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Rol seleccionarPorNickname(String nickname) {
        try {
            return this.entityManager
                    .createQuery("SELECT r FROM Rol r JOIN r.usuarios u WHERE u.nickname = :nickname", Rol.class)
                    .setParameter("nickname", nickname).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
