package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.PreguntaFrecuente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class PreguntaFrecuenteRepositoryImpl implements IPreguntaFrecuenteRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(PreguntaFrecuente preguntaFrecuente) {
        this.entityManager.persist(preguntaFrecuente);
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(PreguntaFrecuente preguntaFrecuente) {
        this.entityManager.merge(preguntaFrecuente);
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public List<PreguntaFrecuente> seleccionarTodo() {
        return this.entityManager.createQuery("SELECT p FROM PreguntaFrecuente p", PreguntaFrecuente.class).getResultList();
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public PreguntaFrecuente seleccionarPorId(Integer id) {
        return this.entityManager.find(PreguntaFrecuente.class, id);
    }

}
