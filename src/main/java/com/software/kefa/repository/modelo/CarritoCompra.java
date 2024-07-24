package com.software.kefa.repository.modelo;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Represents a shopping cart for a user.
 * 
 * This class contains information about the items in the shopping cart,
 * the quantity of each item, the selected date for the purchase, and the user who owns the cart.
 * It also includes a list of detailed orders associated with the cart.
 */
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

    @Column(name = "cdco_fecha_seleccionada")
    private LocalDateTime fechaSeleccionada;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cdco_id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "carritoCompra", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DetalleOrden> detalleOrden;

    public CarritoCompra() {
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    public void setFechaSeleccionada(LocalDateTime fechaSeleccionada) {
        this.fechaSeleccionada = fechaSeleccionada;
    }

    public List<DetalleOrden> getDetalleOrden() {
        return detalleOrden;
    }

    public void setDetalleOrden(List<DetalleOrden> detalleOrden) {
        this.detalleOrden = detalleOrden;
    }

}
