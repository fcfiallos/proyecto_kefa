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
 * Represents a Devoluciones object.
 * 
 * This class is used to store information about a devolución (return) in a software project.
 * It contains properties such as id, motivo, estado_del_producto, fechaDevolucion, and usuario.
 */
@Entity
@Table(name = "devolucion")
public class Devoluciones {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_devolucion")
    @SequenceGenerator(name = "seq_devolucion", sequenceName = "seq_devolucion", allocationSize = 1)
    @Column(name = "devo_id")
    private Integer id;

    @Column(name = "devo_motivo", length = 500)
    private String motivo;

    @Column(name = "devo_estado_del_producto")
    private String estado_del_producto;

    @Column(name = "devo_fecha_devolucion")
    private LocalDateTime fechaDevolucion;

    @ManyToOne
    @JoinColumn(name = "devo_id_usuario")
    private Usuario usuario;

    /**
     * Gets the ID of the Devoluciones.
     *
     * @return the ID of the Devoluciones
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the Devoluciones.
     *
     * @param id the ID of the Devoluciones
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the motivo of the Devoluciones.
     *
     * @return the motivo of the Devoluciones
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Sets the motivo of the Devoluciones.
     *
     * @param motivo the motivo of the Devoluciones
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Gets the estado_del_producto of the Devoluciones.
     *
     * @return the estado_del_producto of the Devoluciones
     */
    public String getEstado_del_producto() {
        return estado_del_producto;
    }

    /**
     * Sets the estado_del_producto of the Devoluciones.
     *
     * @param estado_del_producto the estado_del_producto of the Devoluciones
     */
    public void setEstado_del_producto(String estado_del_producto) {
        this.estado_del_producto = estado_del_producto;
    }

    /**
     * Gets the fechaDevolucion of the Devoluciones.
     *
     * @return the fechaDevolucion of the Devoluciones
     */
    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * Sets the fechaDevolucion of the Devoluciones.
     *
     * @param fecha_devolucion the fechaDevolucion of the Devoluciones
     */
    public void setFechaDevolucion(LocalDateTime fecha_devolucion) {
        this.fechaDevolucion = fecha_devolucion;
    }

    /**
     * Gets the usuario of the Devoluciones.
     *
     * @return the usuario of the Devoluciones
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Sets the usuario of the Devoluciones.
     *
     * @param usuario the usuario of the Devoluciones
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
