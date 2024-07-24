package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Pago;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * Implementation of the IMetodoPagoRepository interface that provides methods for
 * inserting, updating, deleting, and selecting payment records using an EntityManager.
 */
@Repository
@Transactional
public class MetodoPagoRepositoryImpl implements IMetodoPagoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
        * Inserts a new Pago entity into the database.
        *
        * @param pago The Pago entity to be inserted.
        */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(Pago pago) {
        this.entityManager.persist(pago);
    }

    /**
        * Updates the given payment in the database.
        *
        * @param pago The payment to be updated.
        */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(Pago pago) {
        this.entityManager.merge(pago);
    }

    /**
        * Deletes the given Pago entity from the database.
        *
        * @param pago The Pago entity to be deleted.
        */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void eliminar(Pago pago) {
        this.entityManager.remove(pago);
    }

    /**
        * Retrieves a Pago object by its ID.
        *
        * @param id The ID of the Pago object to retrieve.
        * @return The Pago object with the specified ID, or null if not found.
        */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Pago seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(Pago.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

}
