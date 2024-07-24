package com.software.kefa.service.modelosto;

/**
 * The OtrosTO class represents a data transfer object for "Otros" entities.
 * It contains information about the comment, return status, and return reason.
 */
public class OtrosTO {
    private String comentario;
    private String estadoDevolucion;
    private String motivoDevolucion;

    /**
     * Default constructor for the OtrosTO class.
     */
    public OtrosTO() {
    }

    /**
     * Constructor for the OtrosTO class with parameters.
     * 
     * @param comentario        The comment for the "Otros" entity.
     * @param estadoDevolucion  The return status for the "Otros" entity.
     * @param motivoDevolucion  The return reason for the "Otros" entity.
     */
    public OtrosTO(String comentario, String estadoDevolucion, String motivoDevolucion) {
        this.comentario = comentario;
        this.estadoDevolucion = estadoDevolucion;
        this.motivoDevolucion = motivoDevolucion;
    }

    /**
     * Get the comment for the "Otros" entity.
     * 
     * @return The comment.
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Set the comment for the "Otros" entity.
     * 
     * @param comentario The comment to set.
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Get the return status for the "Otros" entity.
     * 
     * @return The return status.
     */
    public String getEstadoDevolucion() {
        return estadoDevolucion;
    }

    /**
     * Set the return status for the "Otros" entity.
     * 
     * @param estadoDevolucion The return status to set.
     */
    public void setEstadoDevolucion(String estadoDevolucion) {
        this.estadoDevolucion = estadoDevolucion;
    }

    /**
     * Get the return reason for the "Otros" entity.
     * 
     * @return The return reason.
     */
    public String getMotivoDevolucion() {
        return motivoDevolucion;
    }

    /**
     * Set the return reason for the "Otros" entity.
     * 
     * @param motivoDevolucion The return reason to set.
     */
    public void setMotivoDevolucion(String motivoDevolucion) {
        this.motivoDevolucion = motivoDevolucion;
    }
}
