package com.software.kefa.service;

import java.util.List;

import com.software.kefa.repository.modelo.PreguntaFrecuente;

/**
 * This interface represents the service for managing frequently asked questions.
 */
public interface IPreguntaFrecuenteService {
    
    /**
     * Saves a new frequently asked question.
     * 
     * @param preguntaFrecuente The frequently asked question to be saved.
     * @param nickname The nickname of the user saving the question.
     */
    public void guardar(PreguntaFrecuente preguntaFrecuente, String nickname);
    
    /**
     * Updates an existing frequently asked question.
     * 
     * @param preguntaFrecuente The frequently asked question to be updated.
     */
    public void actualizar(PreguntaFrecuente preguntaFrecuente);
    
    /**
     * Retrieves all the frequently asked questions.
     * 
     * @return A list of all the frequently asked questions.
     */
    public List<PreguntaFrecuente> buscarTodo();
    
    /**
     * Retrieves a frequently asked question by its ID.
     * 
     * @param id The ID of the frequently asked question.
     * @return The frequently asked question with the specified ID, or null if not found.
     */
    public PreguntaFrecuente buscarPorId(Integer id);
}
