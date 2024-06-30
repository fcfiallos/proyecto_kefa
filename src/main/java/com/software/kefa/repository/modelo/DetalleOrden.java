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
@Table(name = "detalle_orden")
public class DetalleOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_detalle_orden")
    @SequenceGenerator(name = "seq_detalle_orden", sequenceName = "seq_detalle_orden", allocationSize = 1)
    @Column(name = "deor_id")
    private Integer id;

    @Column(name = "deor_cantidad")
    private Integer cantidad;

    @Column(name = "deor_precio_unitario")
    private BigDecimal precioUnitario;

    @Column(name = "deor_descuento")
    private BigDecimal descuento;

    @Column(name = "deor_impuesto")
    private BigDecimal impuesto;

    @Column(name = "deor_total_producto")
    private BigDecimal totalProducto;

    @ManyToOne
    @JoinColumn(name = "deor_id_orden")
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "deor_id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "deor_id_carrito_compra")
    private CarritoCompra carritoCompra;

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

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(BigDecimal impuesto) {
        this.impuesto = impuesto;
    }

    public BigDecimal getTotalProducto() {
        return totalProducto;
    }

    public void setTotalProducto(BigDecimal totalProducto) {
        this.totalProducto = totalProducto;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public CarritoCompra getCarritoCompra() {
        return carritoCompra;
    }

    public void setCarritoCompra(CarritoCompra carritoCompra) {
        this.carritoCompra = carritoCompra;
    }

}
