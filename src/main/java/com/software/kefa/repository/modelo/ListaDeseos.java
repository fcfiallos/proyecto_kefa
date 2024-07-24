package com.software.kefa.repository.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    @Column(name = "lide_fecha_seleccionada")
    private LocalDateTime fechaSeleccionada;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lide_id_usua")
    private Usuario usuario;

    /**
     * Gets the ID of the ListaDeseos.
     *
     * @return the ID of the ListaDeseos
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the ListaDeseos.
     *
     * @param id the ID of the ListaDeseos
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the fechaSeleccionada of the ListaDeseos.
     *
     * @return the fechaSeleccionada of the ListaDeseos
     */
    public LocalDateTime getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    /**
     * Sets the fechaSeleccionada of the ListaDeseos.
     *
     * @param fechaSeleccionada the fechaSeleccionada of the ListaDeseos
     */
    public void setFechaSeleccionada(LocalDateTime fechaSeleccionada) {
        this.fechaSeleccionada = fechaSeleccionada;
    }

    /**
     * Gets the Usuario associated with the ListaDeseos.
     *
     * @return the Usuario associated with the ListaDeseos
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Sets the Usuario associated with the ListaDeseos.
     *
     * @param usuario the Usuario associated with the ListaDeseos
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
