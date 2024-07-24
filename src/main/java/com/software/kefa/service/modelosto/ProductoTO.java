package com.software.kefa.service.modelosto;

/**
 * Represents a product transfer object (TO) that holds information about a product.
 */
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
    private Integer categoriaId;

    /**
     * Default constructor for the ProductoTO class.
     */
    public ProductoTO() {
    }

    /**
     * Constructor for the ProductoTO class with all the attributes.
     *
     * @param nombre          The name of the product.
     * @param descripcion     The description of the product.
     * @param codigo          The code of the product.
     * @param nombreProveedor The name of the product's supplier.
     * @param precio          The price of the product.
     * @param cantidad        The quantity of the product.
     * @param imagen          The image URL of the product.
     * @param pais            The country of origin of the product.
     * @param tipo            The type of the product.
     * @param categoriaId     The category ID of the product.
     */
    public ProductoTO(String nombre, String descripcion, String codigo, String nombreProveedor,
                      String precio, Integer cantidad, String imagen, String pais, String tipo, Integer categoriaId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.nombreProveedor = nombreProveedor;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagen = imagen;
        this.pais = pais;
        this.tipo = tipo;
        this.categoriaId = categoriaId;
    }

    /**
     * Gets the price of the product.
     *
     * @return The price of the product.
     */
    public String getPrecio() {
        return precio;
    }

    /**
     * Sets the price of the product.
     *
     * @param precio The price of the product.
     */
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    /**
     * Gets the name of the product.
     *
     * @return The name of the product.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the name of the product.
     *
     * @param nombre The name of the product.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the description of the product.
     *
     * @return The description of the product.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the description of the product.
     *
     * @param descripcion The description of the product.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the code of the product.
     *
     * @return The code of the product.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the code of the product.
     *
     * @param codigo The code of the product.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Gets the name of the product's supplier.
     *
     * @return The name of the product's supplier.
     */
    public String getNombreProveedor() {
        return nombreProveedor;
    }

    /**
     * Sets the name of the product's supplier.
     *
     * @param nombreProveedor The name of the product's supplier.
     */
    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    /**
     * Gets the quantity of the product.
     *
     * @return The quantity of the product.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Sets the quantity of the product.
     *
     * @param cantidad The quantity of the product.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Gets the image URL of the product.
     *
     * @return The image URL of the product.
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Sets the image URL of the product.
     *
     * @param imagen The image URL of the product.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Gets the country of origin of the product.
     *
     * @return The country of origin of the product.
     */
    public String getPais() {
        return pais;
    }

    /**
     * Sets the country of origin of the product.
     *
     * @param pais The country of origin of the product.
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Gets the type of the product.
     *
     * @return The type of the product.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the type of the product.
     *
     * @param tipo The type of the product.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Gets the category ID of the product.
     *
     * @return The category ID of the product.
     */
    public Integer getCategoriaId() {
        return categoriaId;
    }

    /**
     * Sets the category ID of the product.
     *
     * @param categoriaId The category ID of the product.
     */
    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }
}
