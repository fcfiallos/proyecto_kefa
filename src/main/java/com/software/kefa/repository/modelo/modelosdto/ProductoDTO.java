package com.software.kefa.repository.modelo.modelosdto;

import java.math.BigDecimal;

public class ProductoDTO {
    private String nombre;
    private String descripcion;
    private String codigo;
    private String estado;
    private String nombreProveedor;
    private Integer cantidad;
    private BigDecimal precio;

    public ProductoDTO() {
    }
 
    public ProductoDTO(String nombre, String descripcion, String codigo, String estado, String nombreProveedor,
            Integer cantidad, BigDecimal precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.estado = estado;
        this.nombreProveedor = nombreProveedor;
        this.cantidad = cantidad;
        this.precio = precio;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
 
}