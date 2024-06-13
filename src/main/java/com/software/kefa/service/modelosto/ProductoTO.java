package com.software.kefa.service.modelosto;

public class ProductoTO {
    private String nombre;
    private String descripcion;
    private String codigo;
    private String nombreProveedor;
    private String precio;
    private Integer cantidad;
    private String imagen;
    private String pais;
    private String tipo;

    public ProductoTO() {
    }

    public ProductoTO(String nombre, String descripcion, String codigo, String nombreProveedor,
            String precio, Integer cantidad, String imagen, String pais, String tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.nombreProveedor = nombreProveedor;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagen = imagen;
        this.pais = pais;
        this.tipo = tipo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
