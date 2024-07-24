package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Comentario;

/**
 * This interface represents a repository for managing comments.
 */
public interface IComentarioRepository {
    
    /**
     * Inserts a new comment into the repository.
     * 
     * @param comentario The comment to be inserted.
     */
    public void insertar(Comentario comentario);
}
