package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CarritoCompraRepositoryImpl implements ICarritoCompraRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(CarritoCompra carritoCompra) {
        this.entityManager.persist(carritoCompra);
    }

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

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public List<Producto> seleccionarTodo() {
        return this.entityManager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }

}

