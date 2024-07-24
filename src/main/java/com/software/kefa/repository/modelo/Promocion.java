package com.software.kefa.repository.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Represents a promotional offer in the system.
 */
@Entity
@Table(name = "promocion")
public class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_promocion")
    @SequenceGenerator(name = "seq_promocion", sequenceName = "seq_promocion", allocationSize = 1)
    @Column(name = "prom_id")
    private Integer id;

    @Column(name = "prom_tipo_promocion")
    private String tipoPromocion;

    @Column(name = "prom_descuento")
    private BigDecimal descuento;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "prom_fecha_inicio")
    private LocalDate fechaInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "prom_fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "prom_estado")
    private String estado;

    @OneToMany(mappedBy = "promocion", cascade = CascadeType.ALL)
    private List<DetalleOrden> detalleOrden;

    /**
     * Gets the ID of the promotional offer.
     *
     * @return the ID of the promotional offer
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the promotional offer.
     *
     * @param id the ID of the promotional offer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the type of the promotional offer.
     *
     * @return the type of the promotional offer
     */
    public String getTipoPromocion() {
        return tipoPromocion;
    }

    /**
     * Sets the type of the promotional offer.
     *
     * @param tipoPromocion the type of the promotional offer
     */
    public void setTipoPromocion(String tipoPromocion) {
        this.tipoPromocion = tipoPromocion;
    }

    /**
     * Gets the discount of the promotional offer.
     *
     * @return the discount of the promotional offer
     */
    public BigDecimal getDescuento() {
        return descuento;
    }

    /**
     * Sets the discount of the promotional offer.
     *
     * @param descuento the discount of the promotional offer
     */
    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    /**
     * Gets the start date of the promotional offer.
     *
     * @return the start date of the promotional offer
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Sets the start date of the promotional offer.
     *
     * @param fechaInicio the start date of the promotional offer
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Gets the end date of the promotional offer.
     *
     * @return the end date of the promotional offer
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Sets the end date of the promotional offer.
     *
     * @param fechaFin the end date of the promotional offer
     */
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Gets the state of the promotional offer.
     *
     * @return the state of the promotional offer
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the state of the promotional offer.
     *
     * @param estado the state of the promotional offer
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Gets the list of order details associated with the promotional offer.
     *
     * @return the list of order details associated with the promotional offer
     */
    public List<DetalleOrden> getDetalleOrden() {
        return detalleOrden;
    }

    /**
     * Sets the list of order details associated with the promotional offer.
     *
     * @param detalleOrden the list of order details associated with the promotional offer
     */
    public void setDetalleOrden(List<DetalleOrden> detalleOrden) {
        this.detalleOrden = detalleOrden;
    }
}
