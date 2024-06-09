package com.software.kefa.service.modelosto;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;
 
public class ProductoTO {
    private String nombre;
    private String descripcion;
    private String codigo;
    private String estado;
    private String nombreProveedor;
    private BigDecimal precio;
    private Integer cantidad;
    private byte [] imagenByte;
    private MultipartFile imagen;
 
    public ProductoTO(){
    }
 
    
    public ProductoTO(String nombre, String descripcion, String codigo, String estado, String nombreProveedor,
            BigDecimal precio, Integer cantidad, byte[] imagenByte, MultipartFile imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.estado = estado;
        this.nombreProveedor = nombreProveedor;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagenByte = imagenByte;
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


    public byte[] getImagenByte() {
        return imagenByte;
    }


    public void setImagenBytes(byte[] imagenByte) {
        this.imagenByte = imagenByte;
    }


    public MultipartFile getImagen() {
        return imagen;
    }


    public void setImagen(MultipartFile imagen) {
        this.imagen = imagen;
    }

    
    
}