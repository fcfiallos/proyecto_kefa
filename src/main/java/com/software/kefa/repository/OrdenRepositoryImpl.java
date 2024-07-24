package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Orden;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 * Implementation of the IOrdenRepository interface that provides methods for interacting with the Orden entity in the database.
 */
@Repository
@Transactional
public class OrdenRepositoryImpl implements IOrdenRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Inserts a new Orden entity into the database.
     *
     * @param orden The Orden object to be inserted.
     */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(Orden orden) {
        this.entityManager.persist(orden);
    }

    /**
     * Updates an existing Orden entity in the database.
     *
     * @param orden The Orden object to be updated.
     */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(Orden orden) {
        this.entityManager.merge(orden);
    }

    /**
     * Retrieves an Orden entity from the database based on the given codigo.
     *
     * @param codigo The codigo of the Orden to be retrieved.
     * @return The Orden object with the given codigo, or null if not found.
     */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Orden seleccionarPorCodigo(String codigo) {
        TypedQuery<Orden> myQuery = this.entityManager.createQuery("SELECT o FROM Orden o WHERE o.codigo = :codigo",
                Orden.class);
        myQuery.setParameter("codigo", codigo);
        return myQuery.getSingleResult();
    }

    /**
     * Retrieves an Orden entity from the database based on the given id.
     *
     * @param id The id of the Orden to be retrieved.
     * @return The Orden object with the given id, or null if not found.
     */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Orden seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(Orden.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Retrieves all Orden entities from the database.
     *
     * @return The list of all Orden objects in the database, or null if none found.
     */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Orden seleccionarTodo() {
        try {
            return this.entityManager.createQuery("SELECT o FROM Orden o", Orden.class).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
