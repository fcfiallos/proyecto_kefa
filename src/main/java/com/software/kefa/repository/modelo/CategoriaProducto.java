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

@Entity
@Table(name = "categoria_producto")
public class CategoriaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cate_producto")
    @SequenceGenerator(name = "seq_cate_producto", sequenceName = "seq_cate_producto", allocationSize = 1)
    @Column (name = "cate_prod_id")
    private Integer id;
    @Column (name = "cate_prod_tipo")
    private String tipo;
    @Column (name = "cate_prod_descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "categoria_producto")
    private List<Producto> productos;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    
}
