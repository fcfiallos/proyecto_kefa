package com.software.kefa.repository.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Represents a session registration.
 */
@Entity
@Table(name = "registro_sesion")
public class RegistroSesion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_registro_sesion")
    @SequenceGenerator(name = "seq_registro_sesion", sequenceName = "seq_registro_sesion", allocationSize = 1)
    @Column(name = "rese_id")
    private Integer id;

    @Column(name = "rese_fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "rese_fecha_fin")
    private LocalDateTime fechaFin;

    @Column(name = "rese_estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "_rese_id_usua")
    private Usuario usuario;

    /**
     * Gets the ID of the session registration.
     *
     * @return The ID of the session registration.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the session registration.
     *
     * @param id The ID of the session registration.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the estado (status) of the session registration.
     *
     * @return The estado (status) of the session registration.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the estado (status) of the session registration.
     *
     * @param estado The estado (status) of the session registration.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Gets the usuario (user) associated with the session registration.
     *
     * @return The usuario (user) associated with the session registration.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Sets the usuario (user) associated with the session registration.
     *
     * @param usuario The usuario (user) associated with the session registration.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Gets the fechaInicio (start date) of the session registration.
     *
     * @return The fechaInicio (start date) of the session registration.
     */
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Sets the fechaInicio (start date) of the session registration.
     *
     * @param fechaInicio The fechaInicio (start date) of the session registration.
     */
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Gets the fechaFin (end date) of the session registration.
     *
     * @return The fechaFin (end date) of the session registration.
     */
    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    /**
     * Sets the fechaFin (end date) of the session registration.
     *
     * @param fechaFin The fechaFin (end date) of the session registration.
     */
    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
}
