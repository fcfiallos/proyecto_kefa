package com.software.kefa.repository.modelo;

import java.time.LocalDateTime;
import java.util.List;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_proveedor")
    @SequenceGenerator(name = "seq_proveedor", sequenceName = "seq_proveedor", allocationSize = 1)
    @Column(name = "prov_id")
 
    private String id;
    @Column(name = "prov_nombre")
    private String nombre;
    @Column(name = "prov_pais")
    private String pais;
    @Column(name = "prov_tipo")
    private String tipo;
    @Column(name = "prov_fecha")
    private LocalDateTime fechaRegistro;
    @OneToMany(mappedBy = "proveedor")
    private List<Producto> productos;
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    public String getPais() {
        return pais;
    }
 
    public void setPais(String pais) {
        this.pais = pais;
    }
 
    public String getTipo() {
        return tipo;
    }
 
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
 
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
 
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
 
    public List<Producto> getProductos() {
        return productos;
    }
 
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
 
}