package com.software.kefa.repository;

import java.util.List;

import com.software.kefa.repository.modelo.PreguntaFrecuente;

/**
 * This interface represents a repository for managing frequently asked questions (PreguntaFrecuente) entities.
 */
public interface IPreguntaFrecuenteRepository {
    
    /**
     * Inserts a new frequently asked question into the repository.
     *
     * @param preguntaFrecuente The frequently asked question to insert.
     */
    public void insertar(PreguntaFrecuente preguntaFrecuente);
    
    /**
     * Updates an existing frequently asked question in the repository.
     *
     * @param preguntaFrecuente The frequently asked question to update.
     */
    public void actualizar(PreguntaFrecuente preguntaFrecuente);
    
    /**
     * Retrieves all the frequently asked questions from the repository.
     *
     * @return A list of all the frequently asked questions.
     */
    public List<PreguntaFrecuente> seleccionarTodo();
    
    /**
     * Retrieves a frequently asked question by its ID from the repository.
     *
     * @param id The ID of the frequently asked question to retrieve.
     * @return The frequently asked question with the specified ID, or null if not found.
     */
    public PreguntaFrecuente seleccionarPorId(Integer id);
}
