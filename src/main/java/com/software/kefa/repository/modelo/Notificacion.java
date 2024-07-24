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
 * Represents a notification.
 */
@Entity
@Table(name = "notificacion")
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_notificacion")
    @SequenceGenerator(name = "seq_notificacion", sequenceName = "seq_notificacion", allocationSize = 1)
    @Column(name = "noti_id")
    private Integer id;

    @Column(name = "noti_tipo")
    private String tipo;

    @Column(name = "noti_mensaje")
    private String mensaje;

    @Column(name = "noti_fecha")
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "noti_id_usuario")
    private Usuario usuario;

    /**
     * Gets the ID of the notification.
     *
     * @return the ID of the notification
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the notification.
     *
     * @param id the ID of the notification
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the type of the notification.
     *
     * @return the type of the notification
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the type of the notification.
     *
     * @param tipo the type of the notification
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Gets the message of the notification.
     *
     * @return the message of the notification
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets the message of the notification.
     *
     * @param mensaje the message of the notification
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Gets the date and time of the notification.
     *
     * @return the date and time of the notification
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Sets the date and time of the notification.
     *
     * @param fecha the date and time of the notification
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Gets the user associated with the notification.
     *
     * @return the user associated with the notification
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Sets the user associated with the notification.
     *
     * @param usuario the user associated with the notification
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
