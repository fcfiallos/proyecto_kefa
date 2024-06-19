package com.software.kefa.repository.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrito_compra")
public class CarritoCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_carrito_compra")
    @SequenceGenerator(name = "seq_carrito_compra", sequenceName = "seq_carrito_compra", allocationSize = 1)
    @Column(name = "cdco_id")
    private Integer id;

    @Column(name = "cdco_cantidad")
    private Integer cantidad;

    @OneToMany(mappedBy = "carritoCompra")
    private List<Producto> productos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cdco_id_usuario")
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
