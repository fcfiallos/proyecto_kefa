package com.software.kefa.repository.modelo;

import java.math.BigDecimal;
 
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
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_producto")
    @SequenceGenerator(name = "seq_producto", sequenceName = "seq_producto", allocationSize = 1)
    @Column (name = "prod_id")
    private Integer id;
 
    @Column(name = "prod_nombre")
    private String nombre;
 
    @Column(name = "prod_precio")
    private BigDecimal precio;
 
    @Column(name = "prod_descripcion")
    private String descripcion;
 
    @Column(name = "prod_codigo")
    private String codigo;
 
    @Column(name = "prod_cantidad")
    private Integer cantidad;
 
    @Column(name = "prod_imagen")
    private String imagen;
 
    @Column(name = "prod_estado")
    private String estado;
 
    @ManyToOne
    @JoinColumn(name = "prod_id_proveedor")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "prod_id_usuario")
    private Usuario usuario; 

    
 
    
    public String getImagen() {
        return imagen;
    }
 
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
 
    public Proveedor getProveedor() {
        return proveedor;
    }
 
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
 
    public Usuario getUsuario() {
        return usuario;
    }
 
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    public BigDecimal getPrecio() {
        return precio;
    }
 
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
 
    public String getDescripcion() {
        return descripcion;
    }
 
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
 
    public String getCodigo() {
        return codigo;
    }
 
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
 
    public Integer getCantidad() {
        return cantidad;
    }
 
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
 
    public String getEstado() {
        return estado;
    }
 
    public void setEstado(String estado) {
        this.estado = estado;
    }
 
    

}