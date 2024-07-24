package com.software.kefa.repository.modelo;

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

/**
 * Represents a location entity.
 * This class is used to store information about a location, including its ID, postal code, city, province, address, and associated user.
 */
@Entity
@Table(name = "ubicacion")
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ubicacion")
    @SequenceGenerator(name = "seq_ubicacion", sequenceName = "seq_ubicacion", allocationSize = 1)
    @Column(name = "ubic_id")
    private Integer id;

    @Column(name = "ubic_codigo_postal")
    private String codigoPostal;

    @Column(name = "ubic_ciudad")
    private String ciudad;

    @Column(name = "ubic_provincia")
    private String provincia;

    @Column(name = "ubic_direccion")
    private String direccion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ubic_id_usuario")
    private Usuario usuario;

    /**
     * Gets the identifier of the Ubicacion.
     *
     * @return the identifier
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the identifier of the Ubicacion.
     *
     * @param id the identifier to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the postal code of the Ubicacion.
     *
     * @return the postal code
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Sets the postal code of the Ubicacion.
     *
     * @param codigoPostal the postal code to set
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Gets the city of the Ubicacion.
     *
     * @return the city
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Sets the city of the Ubicacion.
     *
     * @param ciudad the city to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Gets the province of the Ubicacion.
     *
     * @return the province
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Sets the province of the Ubicacion.
     *
     * @param provincia the province to set
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Gets the address of the Ubicacion.
     *
     * @return the address
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets the address of the Ubicacion.
     *
     * @param direccion the address to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Gets the associated Usuario of the Ubicacion.
     *
     * @return the associated Usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Sets the associated Usuario of the Ubicacion.
     *
     * @param usuario the associated Usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
