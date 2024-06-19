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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    

}
