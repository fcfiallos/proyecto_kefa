package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.DetalleOrden;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * This class implements the IDetalleOrdenRepository interface and provides
 * the implementation for the repository methods related to DetalleOrden entity.
 * It uses the EntityManager to interact with the database and perform CRUD operations.
 */
@Repository
@Transactional
public class DetalleOrdenRepositoryImpl implements IDetalleOrdenRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
        * Inserts a new DetalleOrden into the database.
        *
        * @param detalleOrden The DetalleOrden object to be inserted.
        */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(DetalleOrden detalleOrden) {
        this.entityManager.persist(detalleOrden);
    }

    /**
        * Updates the given DetalleOrden entity in the database.
        *
        * @param detalleOrden The DetalleOrden entity to be updated.
        */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(DetalleOrden detalleOrden) {
        this.entityManager.merge(detalleOrden);
    }

    /**
        * Retrieves a DetalleOrden object by its ID.
        *
        * @param id The ID of the DetalleOrden object to retrieve.
        * @return The DetalleOrden object with the specified ID, or null if not found.
        */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public DetalleOrden seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(DetalleOrden.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
        * Removes the specified `DetalleOrden` from the repository.
        *
        * @param detalleOrden The `DetalleOrden` to be removed.
        */
    @Override
    public void eliminar(DetalleOrden detalleOrden) {
        this.entityManager.remove(detalleOrden);
    }

    /**
        * Retrieves the DetalleOrden object associated with the given carritoCompra ID.
        * 
        * @param idCarrito The ID of the carritoCompra.
        * @return The DetalleOrden object associated with the given carritoCompra ID, or null if not found.
        */
    @Override
    public DetalleOrden seleccionarPorIdCarrito(Integer idCarrito) {
        try {
            return this.entityManager
                    .createQuery("SELECT d FROM DetalleOrden d WHERE d.carritoCompra.id = :idCarrito",
                            DetalleOrden.class)
                    .setParameter("idCarrito", idCarrito).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
        * Guarda o actualiza un objeto DetalleOrden en la base de datos.
        * Si el objeto DetalleOrden tiene un ID nulo, se guarda como una nueva entidad.
        * Si el objeto DetalleOrden tiene un ID existente, se actualiza la entidad correspondiente en la base de datos.
        *
        * @param detalleOrden el objeto DetalleOrden a guardar o actualizar
        * @return el objeto DetalleOrden guardado o actualizado
        */
    @Override
    public DetalleOrden guardarOActualizarDetalleOrden(DetalleOrden detalleOrden) {
        if (detalleOrden.getId() == null) {
            this.entityManager.persist(detalleOrden);
            return detalleOrden;
        } else {
            return this.entityManager.merge(detalleOrden);
        }
    }

}
