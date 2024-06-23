package com.software.kefa.repository.modelo.modelosdto;

public class CategoriaProductoDTO {
    private String tipo;
    private String descripcion;
    private String imagen;
    private String nickmane;;

    public CategoriaProductoDTO() {
    }
 
    public CategoriaProductoDTO(String tipo, String descripcion, String imagen, String nickmane) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.nickmane = nickmane;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNickmane() {
        return nickmane;
    }

    public void setNickmane(String nickmane) {
        this.nickmane = nickmane;
    }
    
 
}