package com.software.kefa.repository.modelo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Representa un producto en el sistema.
 */
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_producto")
    @SequenceGenerator(name = "seq_producto", sequenceName = "seq_producto", allocationSize = 1)
    @Column(name = "prod_id")
    private Integer id;

    @Column(name = "prod_nombre")
    private String nombre;

    @Column(name = "prod_precio")
    private BigDecimal precio;

    @Column(name = "prod_descripcion")
    private String descripcion;

    @Column(name = "prod_codigo", unique = true)
    private String codigo;

    @Column(name = "prod_cantidad")
    private Integer cantidad;

    @Column(name = "prod_imagen")
    private String imagen;

    @Column(name = "prod_estado")
    private String estado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prod_id_proveedor")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "prod_id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "prod_id_cate_producto")
    private CategoriaProducto categoriaProducto;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<DetalleOrden> detalleOrden;

    @ManyToMany(mappedBy = "producto")
    @JoinTable(name = "producto_promocion", joinColumns = @JoinColumn(name = "prod_id"), inverseJoinColumns = @JoinColumn(name = "prom_id"))
    private Set<Promocion> promocion;

    @ManyToOne
    @JoinColumn(name = "prod_id_lista_deseos")
    private ListaDeseos listaDeseos;

    @ManyToOne
    @JoinColumn(name = "prod_id_carrito_compra")
    private CarritoCompra carritoCompra;

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

    public CategoriaProducto getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(CategoriaProducto categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<DetalleOrden> getDetalleOrden() {
        return detalleOrden;
    }

    public void setDetalleOrden(List<DetalleOrden> detalleOrden) {
        this.detalleOrden = detalleOrden;
    }

    public Set<Promocion> getPromocion() {
        return promocion;
    }

    public void setPromocion(Set<Promocion> promocion) {
        this.promocion = promocion;
    }

    public ListaDeseos getListaDeseos() {
        return listaDeseos;
    }

    public void setListaDeseos(ListaDeseos listaDeseos) {
        this.listaDeseos = listaDeseos;
    }

    public CarritoCompra getCarritoCompra() {
        return carritoCompra;
    }

    public void setCarritoCompra(CarritoCompra carritoCompra) {
        this.carritoCompra = carritoCompra;
    }

}