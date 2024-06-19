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
@Table(name = "devolucion")
public class Devolucion {
@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_devolucion")
    @SequenceGenerator(name = "seq_devolucion", sequenceName = "seq_devolucion", allocationSize = 1)
    @Column(name = "devo_id")
    private Integer id;

    @Column(name = "devo_motivo")
    private String motivo;

    @Column(name = "devo_estado_del_producto")
    private String estado_del_producto;

    @Column(name = "devo_fecha_devolucion")
    private LocalDateTime fecha_devolucion;

    @ManyToOne
    @JoinColumn(name = "devo_id_usuario")
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado_del_producto() {
        return estado_del_producto;
    }

    public void setEstado_del_producto(String estado_del_producto) {
        this.estado_del_producto = estado_del_producto;
    }

    public LocalDateTime getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(LocalDateTime fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    

}
