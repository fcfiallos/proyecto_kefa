package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Ubicacion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

/**
 * This class is an implementation of the {@link IUbicacionRepository} interface.
 * It provides methods to interact with the database and perform CRUD operations on the `Ubicacion` entity.
 */
@Repository
@Transactional
public class UbicacionRepositoryImpl implements IUbicacionRepository{
    @PersistenceContext
    private EntityManager entityManager;

    /**
        * Inserts a new Ubicacion entity into the database.
        *
        * @param ubicacion The Ubicacion object to be inserted.
        */
    @Override
    @Transactional(value = TxType.MANDATORY)
    public void insertar(Ubicacion ubicacion) {
        this.entityManager.persist(ubicacion);
    }

}
