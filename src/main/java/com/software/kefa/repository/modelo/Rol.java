package com.software.kefa.repository.modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Represents a role in the system.
 */
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rol")
    @SequenceGenerator(name = "seq_rol", sequenceName = "seq_rol", allocationSize = 1)
    @Column(name = "rol_id")
    private Integer id;

    @Column(name = "rol_nombre")
    private String nombre;

    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;

    /**
     * Gets the ID of the role.
     * 
     * @return The ID of the role.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the role.
     * 
     * @param id The ID of the role.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name of the role.
     * 
     * @return The name of the role.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the name of the role.
     * 
     * @param nombre The name of the role.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the list of users associated with the role.
     * 
     * @return The list of users associated with the role.
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Sets the list of users associated with the role.
     * 
     * @param usuarios The list of users associated with the role.
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
