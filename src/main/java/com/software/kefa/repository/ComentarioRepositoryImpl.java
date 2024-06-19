package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Comentario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ComentarioRepositoryImpl implements IComentarioRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional (value = Transactional.TxType.MANDATORY)
    public void insertar(Comentario comentario) {
        this.entityManager.persist(comentario);
    }

}
