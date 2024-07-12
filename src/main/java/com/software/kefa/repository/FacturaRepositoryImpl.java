package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Factura;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class FacturaRepositoryImpl implements IFacturaRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(Factura factura) {
        this.entityManager.persist(factura);
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(Factura factura) {
        this.entityManager.merge(factura);
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void eliminar(Factura factura) {
        this.entityManager.remove(factura);
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Factura seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(Factura.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Factura seleccionarTodo() {
       return this.entityManager.createQuery("SELECT f FROM Factura f", Factura.class).getSingleResult();
    }

}
