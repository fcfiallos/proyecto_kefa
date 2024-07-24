package com.software.kefa.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.software.kefa.repository.modelo.Promocion;

/**
 * This interface represents a repository for managing Promocion objects.
 */
public interface IPromocionRepository {
    
    /**
     * Retrieves a Promocion object by its ID.
     *
     * @param id The ID of the Promocion to retrieve.
     * @return The Promocion object with the specified ID, or null if not found.
     */
    public Promocion seleccionarPorId(Integer id);

    /**
     * Inserts a new Promocion object into the repository.
     *
     * @param promocion The Promocion object to insert.
     */
    public void insertar(Promocion promocion);

    /**
     * Updates an existing Promocion object in the repository.
     *
     * @param promocion The Promocion object to update.
     */
    public void actualizar(Promocion promocion);

    /**
     * Retrieves a Promocion object by its start and end dates.
     *
     * @param fechaInicio The start date of the Promocion.
     * @param fechaFin The end date of the Promocion.
     * @return The Promocion object with the specified dates, or null if not found.
     */
    public Promocion seleccionarPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    /**
     * Retrieves all Promocion objects from the repository.
     *
     * @return A list of all Promocion objects in the repository.
     */
    public List<Promocion> seleccionarTodo();
}
