package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.PreguntaFrecuente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * Implementation of the IPreguntaFrecuenteRepository interface.
 * Provides methods to insert, update, and retrieve PreguntaFrecuente objects
 * from the database.
 */
@Repository
@Transactional
public class PreguntaFrecuenteRepositoryImpl implements IPreguntaFrecuenteRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
        * Inserts a new PreguntaFrecuente into the database.
        *
        * @param preguntaFrecuente The PreguntaFrecuente object to be inserted.
        */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(PreguntaFrecuente preguntaFrecuente) {
        this.entityManager.persist(preguntaFrecuente);
    }

    /**
        * Updates the given PreguntaFrecuente entity in the database.
        *
        * @param preguntaFrecuente The PreguntaFrecuente entity to be updated.
        */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(PreguntaFrecuente preguntaFrecuente) {
        this.entityManager.merge(preguntaFrecuente);
    }

    /**
        * Retrieves all the PreguntaFrecuente objects from the database.
        *
        * @return a list of PreguntaFrecuente objects
        */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public List<PreguntaFrecuente> seleccionarTodo() {
        return this.entityManager.createQuery("SELECT p FROM PreguntaFrecuente p", PreguntaFrecuente.class)
                .getResultList();
    }

    /**
     * Retrieves a PreguntaFrecuente object from the database based on the provided ID.
     *
     * @param id The ID of the PreguntaFrecuente object to retrieve.
     * @return The PreguntaFrecuente object with the specified ID, or null if not found.
     */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public PreguntaFrecuente seleccionarPorId(Integer id) {
        return this.entityManager.find(PreguntaFrecuente.class, id);
    }

}
