package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.RegistroSesion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * This class represents a repository for managing RegistroSesion entities.
 * It provides methods for inserting, updating, and retrieving RegistroSesion
 * objects from the database.
 */
@Repository
@Transactional
public class RegistroSesionRepository implements IRegistroSesionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Inserts a new RegistroSesion into the database.
     *
     * @param registroSesion The RegistroSesion object to be inserted.
     */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(RegistroSesion registroSesion) {
        this.entityManager.persist(registroSesion);
    }

    /**
     * Updates the given RegistroSesion entity in the database.
     *
     * @param registroSesion The RegistroSesion entity to be updated.
     */
    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(RegistroSesion registroSesion) {
        this.entityManager.merge(registroSesion);
    }

    /**
     * Retrieves a RegistroSesion object from the database based on the specified
     * ID.
     *
     * @param id The ID of the RegistroSesion object to retrieve.
     * @return The RegistroSesion object with the specified ID, or null if not
     *         found.
     */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public RegistroSesion seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(RegistroSesion.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Retrieves the most recent RegistroSesion object associated with the given nickname.
     *
     * @param nickname the nickname of the user
     * @return the most recent RegistroSesion object, or null if no matching RegistroSesion is found
     */
    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public RegistroSesion seleccionarPorNickname(String nickname) {
        List<RegistroSesion> resultados = this.entityManager.createQuery(
                "SELECT r FROM RegistroSesion r WHERE r.usuario.nickname = :nickname ORDER BY r.fechaInicio DESC",
                RegistroSesion.class)
                .setParameter("nickname", nickname)
                .getResultList();
        if (resultados.isEmpty()) {
            return null;
        } else {
            // Devuelve el primer resultado, que es el más reciente debido al ordenamiento
            // en la consulta
            return resultados.get(0);
        }
    }
}
