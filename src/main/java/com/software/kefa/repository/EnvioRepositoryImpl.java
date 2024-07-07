package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Envio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EnvioRepositoryImpl implements IEnvioRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insertar(Envio envio) {
        this.entityManager.persist(envio);
    }

    @Override
    public void actualizar(Envio envio) {
        this.entityManager.merge(envio);
    }

    @Override
    public void eliminar(Envio envio) {
        this.entityManager.remove(envio);
    }

    @Override
    public Envio seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(Envio.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

}
