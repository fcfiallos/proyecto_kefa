package com.software.kefa.service.modelosto;

import java.math.BigDecimal;

public class ProductoTO {
    private String nombre;
    private String descripcion;
    private String codigo;
    private String estado;
    private String nombreProveedor;
    private BigDecimal precio;
    private Integer cantidad;
    private String imagen;

    public ProductoTO(){
        
    }

    public ProductoTO(String nombre, String descripcion, String codigo, String estado, String nombreProveedor,
            BigDecimal precio, Integer cantidad, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.estado = estado;
        this.nombreProveedor = nombreProveedor;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagen = imagen;
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
    public BigDecimal getPrecio() {
        return precio;
    }
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
}
