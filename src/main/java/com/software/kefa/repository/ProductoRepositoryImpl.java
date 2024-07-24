package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

/**
 * This class implements the {@link IProductoRepository} interface and provides
 * the implementation for the repository operations related to the
 * {@link Producto} entity.
 * It uses the JPA EntityManager to interact with the underlying database.
 */
@Repository
@Transactional
public class ProductoRepositoryImpl implements IProductoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
        * Retrieves a list of products based on the given category ID.
        *
        * @param categoriaID the ID of the category to filter the products by
        * @return a list of products that belong to the specified category, or null if no products are found
        */
    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Producto> seleccionarPorCategoriaId(Integer categoriaID) {
        try {
            return this.entityManager
                    .createQuery("SELECT p FROM Producto p WHERE p.categoriaProducto.id = :categoriaID",
                            Producto.class)
                    .setParameter("categoriaID", categoriaID).getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    /**
        * Inserts a new Producto into the database.
        *
        * @param producto The Producto object to be inserted.
        */
    @Override
    @Transactional(value = TxType.MANDATORY)
    public void insertar(Producto producto) {
        this.entityManager.persist(producto);
    }

    /**
        * Actualiza un producto en la base de datos.
        *
        * @param producto El producto a actualizar.
        */
    @Override
    @Transactional(value = TxType.MANDATORY)
    public void actualizar(Producto producto) {
        this.entityManager.merge(producto);
    }

    /**
        * Retrieves a Producto object from the database based on the provided codigo.
        * 
        * @param codigo The codigo of the Producto to retrieve.
        * @return The Producto object with the matching codigo, or null if no such Producto exists.
        */
    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Producto seleccionarPorCodigo(String codigo) {
        try {
            TypedQuery<Producto> query = this.entityManager
                    .createQuery("SELECT p FROM Producto p WHERE p.codigo= :codigo", Producto.class);
            query.setParameter("codigo", codigo);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
        * Retrieves a Producto entity by its ID.
        *
        * @param id The ID of the Producto entity to retrieve.
        * @return The Producto entity with the specified ID, or null if not found.
        */
    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Producto seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(Producto.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
        * Retrieves all the products from the database.
        *
        * @return a list of Producto objects representing all the products in the database.
        */
    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Producto> seleccionarTodo() {
        return this.entityManager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }

    /**
     * Retrieves the quantity of a product based on its ID.
     *
     * @param productoId the ID of the product
     * @return the quantity of the product
     */
    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Integer seleccionarPorCantidadProductos(Integer productoId) {
        return this.entityManager
                .createQuery("SELECT p.cantidad FROM Producto p WHERE p.id = :productoId", Integer.class)
                .setParameter("productoId", productoId).getSingleResult();
    }

}