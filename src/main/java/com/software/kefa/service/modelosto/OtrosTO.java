package com.software.kefa.service.modelosto;

public class OtrosTO {
    private String comentario;
    private String estadoDevolucion;
    private String motivoDevolucion;

    public OtrosTO() {
    }

    public OtrosTO(String comentario, String estadoDevolucion, String motivoDevolucion) {
        this.comentario = comentario;
        this.estadoDevolucion = estadoDevolucion;
        this.motivoDevolucion = motivoDevolucion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstadoDevolucion() {
        return estadoDevolucion;
    }

    public void setEstadoDevolucion(String estadoDevolucion) {
        this.estadoDevolucion = estadoDevolucion;
    }

    public String getMotivoDevolucion() {
        return motivoDevolucion;
    }

    public void setMotivoDevolucion(String motivoDevolucion) {
        this.motivoDevolucion = motivoDevolucion;
    }

    
    
}
