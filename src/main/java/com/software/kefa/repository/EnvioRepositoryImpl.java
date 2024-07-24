package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Envio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * Implementation of the {@link IEnvioRepository} interface that provides
 * methods for inserting, updating, deleting, and selecting {@link Envio} entities.
 */
@Repository
@Transactional
public class EnvioRepositoryImpl implements IEnvioRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Inserts a new {@link Envio} entity into the database.
     *
     * @param envio The {@link Envio} entity to insert.
     */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(Envio envio) {
        this.entityManager.persist(envio);
    }

    /**
     * Updates an existing {@link Envio} entity in the database.
     *
     * @param envio The {@link Envio} entity to update.
     */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(Envio envio) {
        this.entityManager.merge(envio);
    }

    /**
     * Deletes an existing {@link Envio} entity from the database.
     *
     * @param envio The {@link Envio} entity to delete.
     */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void eliminar(Envio envio) {
        this.entityManager.remove(envio);
    }

    /**
     * Retrieves a {@link Envio} entity from the database based on its ID.
     *
     * @param id The ID of the {@link Envio} entity to retrieve.
     * @return The retrieved {@link Envio} entity, or null if not found.
     */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Envio seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(Envio.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }
}
