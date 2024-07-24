package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Factura;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * This class implements the {@link IFacturaRepository} interface and provides the implementation
 * for the repository operations related to the `Factura` entity.
 *
 * It uses the JPA EntityManager to interact with the underlying database.
 */
@Repository
@Transactional
public class FacturaRepositoryImpl implements IFacturaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
        * Inserts a new factura into the database.
        *
        * @param factura The factura object to be inserted.
        */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(Factura factura) {
        this.entityManager.persist(factura);
    }

    /**
        * Actualiza una factura en la base de datos.
        *
        * @param factura La factura a actualizar.
        */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(Factura factura) {
        this.entityManager.merge(factura);
    }

    /**
        * Deletes a factura from the repository.
        *
        * @param factura The factura to be deleted.
        */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void eliminar(Factura factura) {
        this.entityManager.remove(factura);
    }

    /**
        * Retrieves a Factura object from the database based on the provided ID.
        *
        * @param id The ID of the Factura to retrieve.
        * @return The Factura object with the specified ID, or null if not found.
        */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Factura seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(Factura.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
        * Retrieves all the Factura objects from the database.
        *
        * @return The Factura object representing the selected record.
        */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Factura seleccionarTodo() {
        return this.entityManager.createQuery("SELECT f FROM Factura f", Factura.class).getSingleResult();
    }

}
