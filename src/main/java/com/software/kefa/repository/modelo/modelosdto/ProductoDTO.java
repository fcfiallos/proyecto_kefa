package com.software.kefa.repository.modelo.modelosdto;

public class ProductoDTO {
    private String nombre;
    private String descripcion;
    private String codigo;
    private String estado;
    private String nombreProveedor;

    public ProductoDTO(String nombre, String descripcion, String codigo, String estado, String nombreProveedor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.estado = estado;
        this.nombreProveedor = nombreProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

}
