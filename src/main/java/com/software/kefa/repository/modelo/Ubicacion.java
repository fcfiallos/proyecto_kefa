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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
