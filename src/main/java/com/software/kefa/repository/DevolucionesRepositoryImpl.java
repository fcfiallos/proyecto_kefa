package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Devoluciones;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * Implementation of the IDevolucionesRepository interface for managing Devoluciones entities.
 */
@Repository
@Transactional
public class DevolucionesRepositoryImpl implements IDevolucionesRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Inserts a Devoluciones entity into the database.
     *
     * @param devolucion The Devoluciones entity to be inserted.
     */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(Devoluciones devolucion) {
        this.entityManager.persist(devolucion);
    }
}
