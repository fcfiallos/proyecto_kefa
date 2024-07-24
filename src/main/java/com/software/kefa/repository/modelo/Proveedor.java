package com.software.kefa.repository.modelo;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Represents a supplier in the system.
 */
@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_proveedor")
    @SequenceGenerator(name = "seq_proveedor", sequenceName = "seq_proveedor", allocationSize = 1)
    @Column(name = "prov_id")
    private Integer id;

    @Column(name = "prov_nombre")
    private String nombre;

    @Column(name = "prov_pais")
    private String pais;

    @Column(name = "prov_tipo")
    private String tipo;

    @Column(name = "prov_fecha")
    private LocalDateTime fechaRegistro;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<Producto> productos;

    /**
     * Gets the name of the supplier.
     * 
     * @return The name of the supplier.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the name of the supplier.
     * 
     * @param nombre The name of the supplier.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the country of the supplier.
     * 
     * @return The country of the supplier.
     */
    public String getPais() {
        return pais;
    }

    /**
     * Sets the country of the supplier.
     * 
     * @param pais The country of the supplier.
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Gets the type of the supplier.
     * 
     * @return The type of the supplier.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the type of the supplier.
     * 
     * @param tipo The type of the supplier.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Gets the registration date of the supplier.
     * 
     * @return The registration date of the supplier.
     */
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Sets the registration date of the supplier.
     * 
     * @param fechaRegistro The registration date of the supplier.
     */
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Gets the list of products associated with the supplier.
     * 
     * @return The list of products associated with the supplier.
     */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
     * Sets the list of products associated with the supplier.
     * 
     * @param productos The list of products associated with the supplier.
     */
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /**
     * Gets the ID of the supplier.
     * 
     * @return The ID of the supplier.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the supplier.
     * 
     * @param id The ID of the supplier.
     */
    public void setId(Integer id) {
        this.id = id;
    }

}