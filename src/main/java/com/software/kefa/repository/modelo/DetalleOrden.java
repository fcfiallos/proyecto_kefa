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

/**
 * Represents a detail of an order.
 */
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

    @ManyToOne
    @JoinColumn(name = "deor_id_promocion")
    private Promocion promocion;

    /**
     * Gets the ID of the detail.
     * 
     * @return the ID of the detail
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the detail.
     * 
     * @param id the ID of the detail
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the quantity of the detail.
     * 
     * @return the quantity of the detail
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Sets the quantity of the detail.
     * 
     * @param cantidad the quantity of the detail
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Gets the unit price of the detail.
     * 
     * @return the unit price of the detail
     */
    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Sets the unit price of the detail.
     * 
     * @param precioUnitario the unit price of the detail
     */
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * Gets the discount of the detail.
     * 
     * @return the discount of the detail
     */
    public BigDecimal getDescuento() {
        return descuento;
    }

    /**
     * Sets the discount of the detail.
     * 
     * @param descuento the discount of the detail
     */
    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    /**
     * Gets the tax of the detail.
     * 
     * @return the tax of the detail
     */
    public BigDecimal getImpuesto() {
        return impuesto;
    }

    /**
     * Sets the tax of the detail.
     * 
     * @param impuesto the tax of the detail
     */
    public void setImpuesto(BigDecimal impuesto) {
        this.impuesto = impuesto;
    }

    /**
     * Gets the total price of the product in the detail.
     * 
     * @return the total price of the product in the detail
     */
    public BigDecimal getTotalProducto() {
        return totalProducto;
    }

    /**
     * Sets the total price of the product in the detail.
     * 
     * @param totalProducto the total price of the product in the detail
     */
    public void setTotalProducto(BigDecimal totalProducto) {
        this.totalProducto = totalProducto;
    }

    /**
     * Gets the order associated with the detail.
     * 
     * @return the order associated with the detail
     */
    public Orden getOrden() {
        return orden;
    }

    /**
     * Sets the order associated with the detail.
     * 
     * @param orden the order associated with the detail
     */
    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    /**
     * Gets the product in the detail.
     * 
     * @return the product in the detail
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Sets the product in the detail.
     * 
     * @param producto the product in the detail
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Gets the shopping cart associated with the detail.
     * 
     * @return the shopping cart associated with the detail
     */
    public CarritoCompra getCarritoCompra() {
        return carritoCompra;
    }

    /**
     * Sets the shopping cart associated with the detail.
     * 
     * @param carritoCompra the shopping cart associated with the detail
     */
    public void setCarritoCompra(CarritoCompra carritoCompra) {
        this.carritoCompra = carritoCompra;
    }

    /**
     * Gets the promotion applied to the detail.
     * 
     * @return the promotion applied to the detail
     */
    public Promocion getPromocion() {
        return promocion;
    }

    /**
     * Sets the promotion applied to the detail.
     * 
     * @param promocion the promotion applied to the detail
     */
    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }
}
