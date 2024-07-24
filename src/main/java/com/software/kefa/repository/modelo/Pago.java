package com.software.kefa.repository.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Represents a payment entity.
 * This class is used to store information about a payment made by a user.
 */
@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pago")
    @SequenceGenerator(name = "seq_pago", sequenceName = "seq_pago", allocationSize = 1)
    @Column(name = "pago_id")
    private Integer id;

    @Column(name = "pago_comprobante")
    private String comprobante;

    @Column(name = "pago_fecha")
    private LocalDateTime fecha;

    @Column(name = "pago_estado")
    private String estado;

    @OneToOne(mappedBy = "pago", cascade = CascadeType.ALL)
    private Orden orden;

    @ManyToOne
    private Usuario usuario;

    /**
     * Returns the ID of the payment.
     *
     * @return the ID of the payment
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the Pago object.
     *
     * @param id the ID to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the comprobante of the payment.
     *
     * @return the comprobante of the payment
     */
    public String getComprobante() {
        return comprobante;
    }

    /**
     * Sets the comprobante of the Pago object.
     *
     * @param comprobante the comprobante to set
     */
    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    /**
     * Returns the fecha of the payment.
     *
     * @return the fecha of the payment
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Sets the fecha of the Pago object.
     *
     * @param fecha the fecha to set
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Returns the estado of the payment.
     *
     * @return the estado of the payment
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the estado of the Pago object.
     *
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Returns the orden associated with the payment.
     *
     * @return the orden associated with the payment
     */
    public Orden getOrden() {
        return orden;
    }

    /**
     * Sets the orden associated with the Pago object.
     *
     * @param orden the orden to set
     */
    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    /**
     * Returns the usuario associated with the payment.
     *
     * @return the usuario associated with the payment
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Sets the usuario associated with the Pago object.
     *
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
