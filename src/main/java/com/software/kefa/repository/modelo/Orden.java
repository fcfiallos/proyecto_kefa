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

    /**
     * Gets the factura associated with the order.
     *
     * @return the factura associated with the order
     */
    public Factura getFactura() {
        return factura;
    }

    /**
     * Sets the factura associated with the order.
     *
     * @param factura the factura to be set
     */
    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    /**
     * Gets the ID of the order.
     *
     * @return the ID of the order
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the order.
     *
     * @param id the ID to be set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the date and time of the order.
     *
     * @return the date and time of the order
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Sets the date and time of the order.
     *
     * @param fecha the date and time to be set
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Gets the estado of the order.
     *
     * @return the estado of the order
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the estado of the order.
     *
     * @param estado the estado to be set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Gets the usuario associated with the order.
     *
     * @return the usuario associated with the order
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Sets the usuario associated with the order.
     *
     * @param usuario the usuario to be set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Gets the detalleOrden list associated with the order.
     *
     * @return the detalleOrden list associated with the order
     */
    public List<DetalleOrden> getDetalleOrden() {
        return detalleOrden;
    }

    /**
     * Sets the detalleOrden list associated with the order.
     *
     * @param detalleOrden the detalleOrden list to be set
     */
    public void setDetalleOrden(List<DetalleOrden> detalleOrden) {
        this.detalleOrden = detalleOrden;
    }

    /**
     * Gets the totalOrden of the order.
     *
     * @return the totalOrden of the order
     */
    public BigDecimal getTotalOrden() {
        return totalOrden;
    }

    /**
     * Sets the totalOrden of the order.
     *
     * @param totalOrden the totalOrden to be set
     */
    public void setTotalOrden(BigDecimal totalOrden) {
        this.totalOrden = totalOrden;
    }

    /**
     * Gets the totalDetalleOrden of the order.
     *
     * @return the totalDetalleOrden of the order
     */
    public BigDecimal getTotalDetalleOrden() {
        return totalDetalleOrden;
    }

    /**
     * Sets the totalDetalleOrden of the order.
     *
     * @param totalDetalleOrden the totalDetalleOrden to be set
     */
    public void setTotalDetalleOrden(BigDecimal totalDetalleOrden) {
        this.totalDetalleOrden = totalDetalleOrden;
    }

    /**
     * Gets the costoEnvio of the order.
     *
     * @return the costoEnvio of the order
     */
    public BigDecimal getCostoEnvio() {
        return costoEnvio;
    }

    /**
     * Sets the costoEnvio of the order.
     *
     * @param costoEnvio the costoEnvio to be set
     */
    public void setCostoEnvio(BigDecimal costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    /**
     * Gets the codigo of the order.
     *
     * @return the codigo of the order
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the codigo of the order.
     *
     * @param codigo the codigo to be set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Gets the pago associated with the order.
     *
     * @return the pago associated with the order
     */
    public Pago getPago() {
        return pago;
    }

    /**
     * Sets the pago associated with the order.
     *
     * @param pago the pago to be set
     */
    public void setPago(Pago pago) {
        this.pago = pago;
    }

    /**
     * Gets the envio associated with the order.
     *
     * @return the envio associated with the order
     */
    public Envio getEnvio() {
        return envio;
    }

    /**
     * Sets the envio associated with the order.
     *
     * @param envio the envio to be set
     */
    public void setEnvio(Envio envio) {
        this.envio = envio;
    }
}
