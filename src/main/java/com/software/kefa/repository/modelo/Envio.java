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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    
}
