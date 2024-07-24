package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Comentario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * This class implements the {@link IComentarioRepository} interface and provides
 * the functionality to insert a {@link Comentario} entity into the database.
 * It uses the JPA EntityManager to perform the database operations.
 */
@Repository
@Transactional
public class ComentarioRepositoryImpl implements IComentarioRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
        * Inserts a new Comentario entity into the database.
        *
        * @param comentario The Comentario object to be inserted.
        */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(Comentario comentario) {
        this.entityManager.persist(comentario);
    }

}
