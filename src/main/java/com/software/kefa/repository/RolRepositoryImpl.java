package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Rol;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

/**
 * Implementation of the IRolRepository interface for managing roles in the database.
 */
@Repository
@Transactional
public class RolRepositoryImpl implements IRolRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Inserts a new role into the database.
     *
     * @param rol The role to be inserted.
     */
    @Override
    @Transactional(value = TxType.MANDATORY)
    public void insertar(Rol rol) {
        this.entityManager.persist(rol);
    }

    /**
     * Retrieves a role from the database based on the given nickname.
     *
     * @param nickname The nickname of the user associated with the role.
     * @return The role with the given nickname, or null if not found.
     */
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
