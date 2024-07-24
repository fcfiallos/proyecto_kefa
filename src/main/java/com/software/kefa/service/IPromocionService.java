package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.List;

import com.software.kefa.repository.modelo.Promocion;
import com.software.kefa.service.modelosto.PromocionTO;

/**
 * This interface represents the service for managing promotions.
 */
public interface IPromocionService {

    /**
     * Saves a new promotion with the given details.
     *
     * @param promocion The promotion to be saved.
     * @param nickname  The nickname of the user saving the promotion.
     */
    public void guardar(PromocionTO promocion, String nickname);

    /**
     * Updates an existing promotion with the given details.
     *
     * @param promocion The updated promotion.
     */
    public void actualizar(Promocion promocion);

    /**
     * Retrieves a promotion by its ID.
     *
     * @param id The ID of the promotion to retrieve.
     * @return The promotion with the specified ID, or null if not found.
     */
    public Promocion buscarPorId(Integer id);

    /**
     * Retrieves a promotion by its start and end dates.
     *
     * @param fechaInicio The start date of the promotion.
     * @param fechaFin    The end date of the promotion.
     * @return The promotion that falls within the specified date range, or null if not found.
     */
    public Promocion buscarPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    /**
     * Retrieves all promotions.
     *
     * @return A list of all promotions.
     */
    public List<Promocion> buscarTodo();
}
