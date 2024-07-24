package com.software.kefa.service;

import com.software.kefa.service.modelosto.OtrosTO;

/**
 * The IOtrosService interface provides methods for saving comments and returns related to "Otros" objects.
 */
public interface IOtrosService {
    
    /**
     * Saves a comment for an "Otros" object.
     * 
     * @param comentario The comment to be saved.
     * @param nickname The nickname of the user who made the comment.
     */
    public void guardarComentario(OtrosTO comentario, String nickname);
    
    /**
     * Saves a return for an "Otros" object.
     * 
     * @param devolucion The return to be saved.
     * @param nickname The nickname of the user who made the return.
     */
    public void guardarDevolucion(OtrosTO devolucion, String nickname);
}
