package com.software.kefa.repository;

import org.springframework.stereotype.Repository;
 
import com.software.kefa.repository.modelo.Proveedor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

/**
 * This class implements the {@link IProveedorRepository} interface and provides
 * the implementation for the repository operations related to the 'Proveedor' entity.
 * It uses the JPA EntityManager to interact with the underlying database.
 */
@Repository
@Transactional
public class ProveedorRepositoryImpl implements IProveedorRepository{
 
    @PersistenceContext
    private EntityManager entityManager;

    /**
        * Inserts a new Proveedor into the database.
        *
        * @param proveedor The Proveedor object to be inserted.
        */
    @Override
    @Transactional(value = TxType.MANDATORY)
    public void insertar(Proveedor proveedor) {
        this.entityManager.persist(proveedor);
    }
 
    /**
        * Updates the given provider in the database.
        *
        * @param proveedor The provider to be updated.
        */
    @Override
    @Transactional(value = TxType.MANDATORY)
    public void actualizar(Proveedor proveedor) {
        this.entityManager.merge(proveedor);
    }
 
    /**
        * Retrieves a Proveedor object from the database based on the given nombre.
        * 
        * @param nombre The nombre of the Proveedor to retrieve.
        * @return The Proveedor object with the given nombre, or null if no such Proveedor exists.
        */
    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Proveedor seleccionarPorNombre(String nombre) {
        try {
            TypedQuery<Proveedor> query = this.entityManager.createQuery("SELECT p FROM Proveedor p WHERE p.nombre= :nombre", Proveedor.class);
        query.setParameter("nombre", nombre);
        return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
 
}
