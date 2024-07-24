package com.software.kefa.repository.modelo.modelosdto;

import java.math.BigDecimal;

/**
 * Represents a data transfer object for a product.
 */
public class ProductoDTO {
    private String nombre;
    private String descripcion;
    private String codigo;
    private String estado;
    private String nombreProveedor;
    private Integer cantidad;
    private BigDecimal precio;

    /**
     * Default constructor.
     */
    public ProductoDTO() {
    }

    /**
     * Constructs a new ProductoDTO with the specified properties.
     *
     * @param nombre           the name of the product
     * @param descripcion      the description of the product
     * @param codigo           the code of the product
     * @param estado           the state of the product
     * @param nombreProveedor  the name of the product supplier
     * @param cantidad         the quantity of the product
     * @param precio           the price of the product
     */
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

    /**
     * Returns the name of the product.
     *
     * @return the name of the product
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the name of the product.
     *
     * @param nombre the name of the product
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Returns the description of the product.
     *
     * @return the description of the product
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the description of the product.
     *
     * @param descripcion the description of the product
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Returns the code of the product.
     *
     * @return the code of the product
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the code of the product.
     *
     * @param codigo the code of the product
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Returns the state of the product.
     *
     * @return the state of the product
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the state of the product.
     *
     * @param estado the state of the product
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Returns the name of the product supplier.
     *
     * @return the name of the product supplier
     */
    public String getNombreProveedor() {
        return nombreProveedor;
    }

    /**
     * Sets the name of the product supplier.
     *
     * @param nombreProveedor the name of the product supplier
     */
    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    /**
     * Returns the quantity of the product.
     *
     * @return the quantity of the product
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Sets the quantity of the product.
     *
     * @param cantidad the quantity of the product
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Returns the price of the product.
     *
     * @return the price of the product
     */
    public BigDecimal getPrecio() {
        return precio;
    }

    /**
     * Sets the price of the product.
     *
     * @param precio the price of the product
     */
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

}