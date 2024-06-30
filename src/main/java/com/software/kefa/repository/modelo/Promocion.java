package com.software.kefa.repository.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

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

    @Column(name = "prom_fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "prom_fecha_fin")
    private LocalDateTime fechaFin;

    @Column(name = "prom_estado")
    private String estado;

    @OneToMany(mappedBy = "promocion", cascade = CascadeType.ALL)
    private List<ProductoPromocion> productoPromocion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoPromocion() {
        return tipoPromocion;
    }

    public void setTipoPromocion(String tipoPromocion) {
        this.tipoPromocion = tipoPromocion;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ProductoPromocion> getProductoPromocion() {
        return productoPromocion;
    }

    public void setProductoPromocion(List<ProductoPromocion> productoPromocion) {
        this.productoPromocion = productoPromocion;
    }

}
