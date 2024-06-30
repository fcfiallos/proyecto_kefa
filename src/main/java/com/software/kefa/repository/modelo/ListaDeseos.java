package com.software.kefa.repository.modelo;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "lista_deseos")
public class ListaDeseos {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lista_deseos")
    @SequenceGenerator(name = "seq_lista_deseos", sequenceName = "seq_lista_deseos", allocationSize = 1)
    @Column(name = "lide_id")
    private Integer id;

    @Column(name = "lide_cantidad")
    private Integer cantidad;

    @Column(name = "lide_fecha_seleccionada")
    private LocalDateTime fechaSeleccionada;

    @OneToOne (cascade = CascadeType.ALL) 
    @JoinColumn(name = "lide_id_usua")
    private Usuario usuario;

    @OneToMany(mappedBy = "listaDeseos", cascade = CascadeType.ALL)
    private List <Producto> productos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    public void setFechaSeleccionada(LocalDateTime fechaSeleccionada) {
        this.fechaSeleccionada = fechaSeleccionada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

}
