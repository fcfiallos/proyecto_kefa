package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.DetalleOrden;
import com.software.kefa.repository.modelo.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * Implementation of the {@link ICarritoCompraRepository} interface that provides
 * methods to interact with the database for managing carritoCompra entities.
 * This class is annotated with {@link Repository} and {@link Transactional} to
 * indicate that it is a repository component and transactions should be managed
 * for the methods in this class.
 */
@Repository
@Transactional
public class CarritoCompraRepositoryImpl implements ICarritoCompraRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
        * Inserts a CarritoCompra object into the database.
        *
        * @param carritoCompra The CarritoCompra object to be inserted.
        */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(CarritoCompra carritoCompra) {
        this.entityManager.persist(carritoCompra);
    }

    /**
        * Updates the given CarritoCompra entity in the database.
        *
        * @param carritoCompra The CarritoCompra entity to be updated.
        */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(CarritoCompra carritoCompra) {
        this.entityManager.merge(carritoCompra);
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public CarritoCompra seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(CarritoCompra.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
        * Retrieves a list of products associated with a specific shopping cart.
        *
        * @param idCarritoCompra The ID of the shopping cart.
        * @return A list of products associated with the shopping cart, or null if no products are found.
        */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public List<Producto> seleccionarTodo(Integer idCarritoCompra) {
        try {
            String jpql = "SELECT p FROM Producto p JOIN p.detalleOrden do JOIN do.carritoCompra cc WHERE cc.id = :idCarritoCompra";
            return this.entityManager.createQuery(jpql, Producto.class)
                    .setParameter("idCarritoCompra", idCarritoCompra).getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    /**
        * Retrieves the CarritoCompra entity associated with the given user nickname.
        *
        * @param nickname the nickname of the user
        * @return the CarritoCompra entity associated with the user nickname, or null if not found
        */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public CarritoCompra seleccionarPorUsuarioNickname(String nickname) {
        try {
            String jpql = "SELECT cc FROM CarritoCompra cc JOIN cc.usuario u WHERE u.nickname = :nickname";
            return this.entityManager.createQuery(jpql, CarritoCompra.class)
                    .setParameter("nickname", nickname).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    /**
        * Retrieves a list of DetalleOrden objects associated with a given CarritoCompra ID.
        *
        * @param id The ID of the CarritoCompra.
        * @return A list of DetalleOrden objects associated with the CarritoCompra ID, or null if no results are found.
        */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public List<DetalleOrden> seleccionarDetalleOrdenPorCarritoCompraId(Integer id) {
        try {
            return this.entityManager
                    .createQuery("SELECT do FROM DetalleOrden do JOIN do.carritoCompra cc WHERE cc.id = :id",
                            DetalleOrden.class)
                    .setParameter("id", id).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
        * Deletes a CarritoCompra entity from the database.
        *
        * @param carritoCompra the ID of the CarritoCompra entity to be deleted
        */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void eliminar(Integer carritoCompra) {
        CarritoCompra carrito = this.seleccionarPorId(carritoCompra);
        this.entityManager.remove(carrito);
    }

    /**
        * Retrieves a list of CarritoCompra objects based on the given nickname.
        *
        * @param nickname the nickname of the user
        * @return a list of CarritoCompra objects associated with the given nickname, or null if no results are found
        */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public List<CarritoCompra> seleccionarPorNickname(String nickname) {
        try {
            String jpql = "SELECT cc FROM CarritoCompra cc JOIN cc.usuario u WHERE u.nickname = :nickname";
            return this.entityManager.createQuery(jpql, CarritoCompra.class)
                    .setParameter("nickname", nickname).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Merges the given `CarritoCompra` object into the database.
     *
     * @param carritoCompra The `CarritoCompra` object to be merged.
     * @return The merged `CarritoCompra` object.
     */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public CarritoCompra unir(CarritoCompra carritoCompra) {
        return this.entityManager.merge(carritoCompra);
    }

}
