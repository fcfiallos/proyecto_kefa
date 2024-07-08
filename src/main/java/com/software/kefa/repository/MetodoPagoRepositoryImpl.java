package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Pago;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class MetodoPagoRepositoryImpl implements IMetodoPagoRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(Pago pago) {
        this.entityManager.persist(pago);
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(Pago pago) {
        this.entityManager.merge(pago);
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void eliminar(Pago pago) {
        this.entityManager.remove(pago);
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Pago seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(Pago.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

}
