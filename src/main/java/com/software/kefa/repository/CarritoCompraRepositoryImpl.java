package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.CarritoCompra;

import jakarta.persistence.EntityManager;
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

}
