package com.software.kefa.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Promocion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * Implementation of the IPromocionRepository interface that provides
 * database operations for the Promocion entity.
 */
@Repository
@Transactional
public class PromocionRepositoryImpl implements IPromocionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Inserts a new Promocion entity into the database.
     *
     * @param promocion The Promocion entity to insert.
     */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(Promocion promocion) {
        this.entityManager.persist(promocion);
    }

    /**
     * Updates an existing Promocion entity in the database.
     *
     * @param promocion The Promocion entity to update.
     */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(Promocion promocion) {
        this.entityManager.merge(promocion);
    }

    /**
     * Retrieves a Promocion entity from the database based on its ID.
     *
     * @param id The ID of the Promocion entity to retrieve.
     * @return The retrieved Promocion entity, or null if not found.
     */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Promocion seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(Promocion.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Retrieves a Promocion entity from the database based on its start and end dates.
     *
     * @param fechaInicio The start date of the Promocion entity.
     * @param fechaFin    The end date of the Promocion entity.
     * @return The retrieved Promocion entity, or null if not found.
     */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Promocion seleccionarPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        try {
            return this.entityManager
                    .createQuery(
                            "SELECT p FROM Promocion p WHERE p.fechaInicio <= :fechaInicio AND p.fechaFin >= :fechaFin",
                            Promocion.class)
                    .setParameter("fechaInicio", fechaInicio)
                    .setParameter("fechaFin", fechaFin)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Retrieves all Promocion entities from the database.
     *
     * @return A list of all Promocion entities.
     */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public List<Promocion> seleccionarTodo() {
        return this.entityManager.createQuery("SELECT p FROM Promocion p", Promocion.class).getResultList();
    }

}
