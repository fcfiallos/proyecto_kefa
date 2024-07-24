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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(BigDecimal impuesto) {
        this.impuesto = impuesto;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Factura [id=" + id + ", numero=" + numero + ", fechaEmision=" + fechaEmision + ", total=" + total
                + ", impuesto=" + impuesto + ", subtotal=" + subtotal + ", orden=" + orden + ", usuario=" + usuario
                + "]";
    }

}
