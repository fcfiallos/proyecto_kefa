package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Promocion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class PromocionRepositoryImpl implements IPromocionRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(Promocion promocion) {
        this.entityManager.persist(promocion);
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(Promocion promocion) {
        this.entityManager.merge(promocion);
    }

}
