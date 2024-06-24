package com.software.kefa.repository.modelo;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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

    @ManyToMany(mappedBy = "listaDeseos")
    private Set<Producto> productos;

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

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

}
