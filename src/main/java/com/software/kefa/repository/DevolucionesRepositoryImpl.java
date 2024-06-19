package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Devolucion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class DevolucionesRepositoryImpl implements IDevolucionesRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional (value = Transactional.TxType.MANDATORY)
    public void insertar(Devolucion devolucion) {
        this.entityManager.persist(devolucion);
    }

}
