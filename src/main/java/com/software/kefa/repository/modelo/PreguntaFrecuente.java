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

@Entity
@Table(name = "pregunta_frecuente")
public class PreguntaFrecuente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pregunta_frecuente")
    @SequenceGenerator(name = "seq_pregunta_frecuente", sequenceName = "seq_pregunta_frecuente", allocationSize = 1)
    @Column(name = "prfr_id")
    private Integer id;

    @Column(name = "prfr_pregunta")
    private String pregunta;

    @Column(name = "prfr_respuesta")
    private String respuesta;

    @Column(name = "prfr_categoria")
    private String categoria;

    @Column(name = "prfr_fecha_creacion")
    private LocalDateTime fecha_creacion;

    @ManyToOne
    @JoinColumn(name = "prfr_id_usuario")
    private Usuario usuario;

    /**
     * Gets the ID of the pregunta frecuente.
     *
     * @return the ID of the pregunta frecuente
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the pregunta frecuente.
     *
     * @param id the ID of the pregunta frecuente
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the pregunta.
     *
     * @return the pregunta
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * Sets the pregunta.
     *
     * @param pregunta the pregunta
     */
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * Gets the respuesta.
     *
     * @return the respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Sets the respuesta.
     *
     * @param respuesta the respuesta
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Gets the categoria.
     *
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Sets the categoria.
     *
     * @param categoria the categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Gets the fecha de creacion.
     *
     * @return the fecha de creacion
     */
    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    /**
     * Sets the fecha de creacion.
     *
     * @param fecha_creacion the fecha de creacion
     */
    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    /**
     * Gets the usuario.
     *
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Sets the usuario.
     *
     * @param usuario the usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
