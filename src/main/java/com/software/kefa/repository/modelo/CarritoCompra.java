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
 * This class contains information about the items in the shopping cart, such as the quantity, selected date, and associated user.
 * It also includes a list of detailed orders associated with the shopping cart.
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

    /**
     * Represents a shopping cart for a customer.
     */
    public CarritoCompra() {
    }

    /**
     * Returns the ID of the carrito de compra.
     *
     * @return the ID of the carrito de compra
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the carritoCompra.
     *
     * @param id the ID to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the quantity of items in the shopping cart.
     *
     * @return the quantity of items in the shopping cart
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Sets the quantity of items in the shopping cart.
     *
     * @param cantidad the quantity to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Returns the user associated with the shopping cart.
     *
     * @return the user associated with the shopping cart
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Sets the user associated with the shopping cart.
     *
     * @param usuario the user to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Returns the selected date and time for the shopping cart.
     *
     * @return the selected date and time for the shopping cart
     */
    public LocalDateTime getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    /**
     * Sets the selected date and time for the shopping cart.
     *
     * @param fechaSeleccionada the date and time to set
     */
    public void setFechaSeleccionada(LocalDateTime fechaSeleccionada) {
        this.fechaSeleccionada = fechaSeleccionada;
    }

    /**
     * Returns the list of detailed orders in the shopping cart.
     *
     * @return the list of detailed orders in the shopping cart
     */
    public List<DetalleOrden> getDetalleOrden() {
        return detalleOrden;
    }

    /**
     * Sets the list of detailed orders in the shopping cart.
     *
     * @param detalleOrden the list of detailed orders to set
     */
    public void setDetalleOrden(List<DetalleOrden> detalleOrden) {
        this.detalleOrden = detalleOrden;
    }

}
