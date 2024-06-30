package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.DetalleOrden;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class DetalleOrdenRepositoryImpl implements IDetalleOrdenRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional (value = Transactional.TxType.MANDATORY)
    public void insertar(DetalleOrden detalleOrden) {
        this.entityManager.persist(detalleOrden);
    }

    @Override
    @Transactional (value = Transactional.TxType.MANDATORY)
    public void actualizar(DetalleOrden detalleOrden) {
        this.entityManager.merge(detalleOrden);
    }

    @Override
    public DetalleOrden seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(DetalleOrden.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

}
