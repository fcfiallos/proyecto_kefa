package com.software.kefa.repository.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Represents a factura (invoice) in the system.
 * 
 * This class contains information about the factura, such as its ID, number, date of issuance, total amount, tax amount, subtotal amount,
 * associated orden, and associated usuario.
 */
@Entity
@Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_factura")
    @SequenceGenerator(name = "seq_factura", sequenceName = "seq_factura", allocationSize = 1)
    @Column(name = "fact_id")
    private Integer id;

    @Column(name = "fact_numero")
    private String numero;

    @Column(name = "fact_fecha_emision")
    private LocalDateTime fechaEmision;

    @Column(name = "fact_total")
    private BigDecimal total;

    @Column(name = "fact_impuesto")
    private BigDecimal impuesto;

    @Column(name = "fact_subtotal")
    private BigDecimal subtotal;

    @OneToOne
    @JoinColumn(name = "fact_id_orden")
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "fact_id_usuario")
    private Usuario usuario;

    /**
     * Returns the ID of the Factura.
     *
     * @return the ID of the Factura
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the Factura.
     *
     * @param id the ID to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the numero of the Factura.
     *
     * @return the numero of the Factura
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the numero of the Factura.
     *
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Returns the fechaEmision of the Factura.
     *
     * @return the fechaEmision of the Factura
     */
    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Sets the fechaEmision of the Factura.
     *
     * @param fechaEmision the fechaEmision to set
     */
    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * Returns the total of the Factura.
     *
     * @return the total of the Factura
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Sets the total of the Factura.
     *
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * Returns the impuesto of the Factura.
     *
     * @return the impuesto of the Factura
     */
    public BigDecimal getImpuesto() {
        return impuesto;
    }

    /**
     * Sets the impuesto of the Factura.
     *
     * @param impuesto the impuesto to set
     */
    public void setImpuesto(BigDecimal impuesto) {
        this.impuesto = impuesto;
    }

    /**
     * Returns the subtotal of the Factura.
     *
     * @return the subtotal of the Factura
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * Sets the subtotal of the Factura.
     *
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * Returns the orden of the Factura.
     *
     * @return the orden of the Factura
     */
    public Orden getOrden() {
        return orden;
    }

    /**
     * Sets the orden of the Factura.
     *
     * @param orden the orden to set
     */
    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    /**
     * Returns the usuario of the Factura.
     *
     * @return the usuario of the Factura
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Sets the usuario of the Factura.
     *
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
        * Returns a string representation of the Factura object.
        * 
        * @return A string representation of the Factura object.
        */
    @Override
    public String toString() {
        return "Factura [id=" + id + ", numero=" + numero + ", fechaEmision=" + fechaEmision + ", total=" + total
                + ", impuesto=" + impuesto + ", subtotal=" + subtotal + ", orden=" + orden + ", usuario=" + usuario
                + "]";
    }

}
