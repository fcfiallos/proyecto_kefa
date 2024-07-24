package com.software.kefa.repository.modelo;

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
 * Represents an Envio (shipment) entity.
 */
@Entity
@Table(name = "envio")
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_envio")
    @SequenceGenerator(name = "seq_envio", sequenceName = "seq_envio", allocationSize = 1)
    @Column(name = "envi_id")
    private Integer id;

    @Column(name = "envi_tipo")
    private String tipo;

    @Column(name = "envi_direccion")
    private String direccion;

    @Column(name = "envi_estado")
    private String estado;

    @Column(name = "envi_fecha")
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "envi_id_usuario")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "envi_id_orden")
    private Orden orden;

    /**
     * Gets the ID of the Envio.
     * 
     * @return The ID of the Envio.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the Envio.
     * 
     * @param id The ID of the Envio.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the tipo of the Envio.
     * 
     * @return The tipo of the Envio.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the tipo of the Envio.
     * 
     * @param tipo The tipo of the Envio.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Gets the direccion of the Envio.
     * 
     * @return The direccion of the Envio.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets the direccion of the Envio.
     * 
     * @param direccion The direccion of the Envio.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Gets the estado of the Envio.
     * 
     * @return The estado of the Envio.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the estado of the Envio.
     * 
     * @param estado The estado of the Envio.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Gets the fecha of the Envio.
     * 
     * @return The fecha of the Envio.
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Sets the fecha of the Envio.
     * 
     * @param fecha The fecha of the Envio.
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Gets the usuario of the Envio.
     * 
     * @return The usuario of the Envio.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Sets the usuario of the Envio.
     * 
     * @param usuario The usuario of the Envio.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Gets the orden of the Envio.
     * 
     * @return The orden of the Envio.
     */
    public Orden getOrden() {
        return orden;
    }

    /**
     * Sets the orden of the Envio.
     * 
     * @param orden The orden of the Envio.
     */
    public void setOrden(Orden orden) {
        this.orden = orden;
    }
}
