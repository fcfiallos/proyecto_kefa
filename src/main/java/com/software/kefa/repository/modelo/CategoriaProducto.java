package com.software.kefa.repository.modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


/**
 * Represents a category of products.
 * 
 * This class is an entity mapped to the "categoria_producto" table in the database.
 * It contains information about the category, such as its ID, type, description, image, and associated products.
 */
@Entity
@Table(name = "categoria_producto")
public class CategoriaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cate_producto")
    @SequenceGenerator(name = "seq_cate_producto", sequenceName = "seq_cate_producto", allocationSize = 1)
    @Column(name = "cate_prod_id")
    private Integer id;

    @Column(name = "cate_prod_tipo")
    private String tipo;

    @Column(name = "cate_prod_descripcion")
    private String descripcion;

    @Column(name = "cate_prod_imagen")
    private String imagen;

    @OneToMany(mappedBy = "categoriaProducto")
    private List<Producto> productos;

    /**
     * Returns the ID of the CategoriaProducto.
     *
     * @return the ID of the CategoriaProducto
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the CategoriaProducto.
     *
     * @param id the ID of the CategoriaProducto
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the tipo of the CategoriaProducto.
     *
     * @return the tipo of the CategoriaProducto
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the tipo of the CategoriaProducto.
     *
     * @param tipo the tipo of the CategoriaProducto
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Returns the descripcion of the CategoriaProducto.
     *
     * @return the descripcion of the CategoriaProducto
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the descripcion of the CategoriaProducto.
     *
     * @param descripcion the descripcion of the CategoriaProducto
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Returns the productos associated with the CategoriaProducto.
     *
     * @return the productos associated with the CategoriaProducto
     */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
     * Sets the productos associated with the CategoriaProducto.
     *
     * @param productos the productos associated with the CategoriaProducto
     */
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /**
     * Returns the imagen of the CategoriaProducto.
     *
     * @return the imagen of the CategoriaProducto
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Sets the imagen of the CategoriaProducto.
     *
     * @param imagen the imagen of the CategoriaProducto
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}