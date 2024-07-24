package com.software.kefa.repository.modelo;

import java.math.BigDecimal;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Represents an order in the system.
 */
@Entity
@Table(name = "orden")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_orden")
    @SequenceGenerator(name = "seq_orden", sequenceName = "seq_orden", allocationSize = 1)
    @Column(name = "orde_id")
    private Integer id;

    @Column(name = "orde_codigo")
    private String codigo;

    @Column(name = "orde_fecha")
    private LocalDateTime fecha;

    @Column(name = "orde_estado")
    private String estado;

    @Column(name = "orde_total_orden")
    private BigDecimal totalOrden;

    @Column(name = "orde_total_detalle_orden")
    private BigDecimal totalDetalleOrden;

    @Column(name = "orde_costo_envio")
    private BigDecimal costoEnvio;

    @ManyToOne
    @JoinColumn(name = "orde_id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleOrden> detalleOrden;

    @OneToOne
    @JoinColumn(name = "orde_id_pago")
    private Pago pago;

    @OneToOne(mappedBy = "orden", cascade = CascadeType.ALL)
    private Factura factura;

    @OneToOne(mappedBy = "orden", cascade = CascadeType.ALL)
    private Envio envio;

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleOrden> getDetalleOrden() {
        return detalleOrden;
    }

    public void setDetalleOrden(List<DetalleOrden> detalleOrden) {
        this.detalleOrden = detalleOrden;
    }

    public BigDecimal getTotalOrden() {
        return totalOrden;
    }

    public void setTotalOrden(BigDecimal totalOrden) {
        this.totalOrden = totalOrden;
    }

    public BigDecimal getTotalDetalleOrden() {
        return totalDetalleOrden;
    }

    public void setTotalDetalleOrden(BigDecimal totalDetalleOrden) {
        this.totalDetalleOrden = totalDetalleOrden;
    }

    public BigDecimal getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(BigDecimal costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

}
