package com.software.kefa.repository.modelo;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Represents a product in the system.
 * This class contains information about the product, such as its name, price, description, code, quantity, image, and state.
 * It also includes references to the supplier, user, category, and order details associated with the product.
 */
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_producto")
    @SequenceGenerator(name = "seq_producto", sequenceName = "seq_producto", allocationSize = 1)
    @Column(name = "prod_id")
    private Integer id;

    @Column(name = "prod_nombre")
    private String nombre;

    @Column(name = "prod_precio")
    private BigDecimal precio;

    @Column(name = "prod_descripcion")
    private String descripcion;

    @Column(name = "prod_codigo", unique = true)
    private String codigo;

    @Column(name = "prod_cantidad")
    private Integer cantidad;

    @Column(name = "prod_imagen")
    private String imagen;

    @Column(name = "prod_estado")
    private String estado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prod_id_proveedor")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "prod_id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "prod_id_cate_producto")
    private CategoriaProducto categoriaProducto;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<DetalleOrden> detalleOrden;

    /**
     * Returns the provider of this product.
     *
     * @return the provider of this product
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * Sets the provider of this product.
     *
     * @param proveedor the provider of this product
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * Returns the user associated with this product.
     *
     * @return the user associated with this product
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Sets the user associated with this product.
     *
     * @param usuario the user associated with this product
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Returns the ID of this product.
     *
     * @return the ID of this product
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of this product.
     *
     * @param id the ID of this product
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the name of this product.
     *
     * @return the name of this product
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the name of this product.
     *
     * @param nombre the name of this product
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Returns the price of this product.
     *
     * @return the price of this product
     */
    public BigDecimal getPrecio() {
        return precio;
    }

    /**
     * Sets the price of this product.
     *
     * @param precio the price of this product
     */
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    /**
     * Returns the description of this product.
     *
     * @return the description of this product
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the description of this product.
     *
     * @param descripcion the description of this product
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Returns the code of this product.
     *
     * @return the code of this product
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the code of this product.
     *
     * @param codigo the code of this product
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Returns the quantity of this product.
     *
     * @return the quantity of this product
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Sets the quantity of this product.
     *
     * @param cantidad the quantity of this product
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Returns the state of this product.
     *
     * @return the state of this product
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the state of this product.
     *
     * @param estado the state of this product
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Returns the category of this product.
     *
     * @return the category of this product
     */
    public CategoriaProducto getCategoriaProducto() {
        return categoriaProducto;
    }

    /**
     * Sets the category of this product.
     *
     * @param categoriaProducto the category of this product
     */
    public void setCategoriaProducto(CategoriaProducto categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    /**
     * Returns the image of this product.
     *
     * @return the image of this product
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Sets the image of this product.
     *
     * @param imagen the image of this product
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Returns the list of detail orders associated with this product.
     *
     * @return the list of detail orders associated with this product
     */
    public List<DetalleOrden> getDetalleOrden() {
        return detalleOrden;
    }

    /**
     * Sets the list of detail orders associated with this product.
     *
     * @param detalleOrden the list of detail orders associated with this product
     */
    public void setDetalleOrden(List<DetalleOrden> detalleOrden) {
        this.detalleOrden = detalleOrden;
    }
}