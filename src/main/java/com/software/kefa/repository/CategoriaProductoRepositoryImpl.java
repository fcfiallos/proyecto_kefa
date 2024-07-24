package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.CategoriaProducto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

/**
 * This class implements the ICategoriaProductoRepository interface and provides
 * the implementation for the repository operations related to the CategoriaProducto entity.
 * It uses the JPA EntityManager to interact with the database.
 */
@Repository
@Transactional
public class CategoriaProductoRepositoryImpl implements ICategoriaProductoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Retrieves all the CategoriaProducto entities from the database.
     *
     * @return a list of CategoriaProducto objects representing all the records in the database.
     */
    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<CategoriaProducto> seleccionarTodo() {

        return this.entityManager.createQuery("SELECT c FROM CategoriaProducto c", CategoriaProducto.class)
                .getResultList();
    }

    /**
        * Inserts a CategoriaProducto entity into the database.
        *
        * @param producto the CategoriaProducto entity to be inserted
        */
    @Override
    @Transactional(value = TxType.MANDATORY)
    public void insertar(CategoriaProducto producto) {

        this.entityManager.persist(producto);
    }

    /**
        * Actualiza un objeto de tipo CategoriaProducto en la base de datos.
        * 
        * @param producto el objeto CategoriaProducto a actualizar
        */
    @Override
    @Transactional(value = TxType.MANDATORY)
    public void actualizar(CategoriaProducto producto) {

        this.entityManager.merge(producto);
    }

    /**
        * Retrieves a CategoriaProducto entity by its ID.
        *
        * @param id The ID of the CategoriaProducto entity to retrieve.
        * @return The CategoriaProducto entity with the specified ID, or null if not found.
        */
    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public CategoriaProducto seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(CategoriaProducto.class, id);
        } catch (NoResultException e) {
            return null;
        }

    }

}
