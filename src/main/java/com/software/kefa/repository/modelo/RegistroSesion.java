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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}
