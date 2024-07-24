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
 * Represents a comment in the system.
 */
@Entity
@Table(name = "comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comentario")
    @SequenceGenerator(name = "seq_comentario", sequenceName = "seq_comentario", allocationSize = 1)
    @Column(name = "come_id")
    private Integer id;

    @Column(name = "come_comentario", length = 500)
    private String comentario;

    @Column(name = "come_fecha_publicacion")
    private LocalDateTime fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "come_id_usuario")
    private Usuario usuario;

    /**
     * Gets the ID of the comment.
     *
     * @return The ID of the comment.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the comment.
     *
     * @param id The ID of the comment.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the content of the comment.
     *
     * @return The content of the comment.
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Sets the content of the comment.
     *
     * @param comentario The content of the comment.
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Gets the publication date of the comment.
     *
     * @return The publication date of the comment.
     */
    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * Sets the publication date of the comment.
     *
     * @param fechaPublicacion The publication date of the comment.
     */
    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * Gets the user who made the comment.
     *
     * @return The user who made the comment.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Sets the user who made the comment.
     *
     * @param usuario The user who made the comment.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
