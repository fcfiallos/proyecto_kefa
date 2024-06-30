package com.software.kefa.repository.modelo;

import java.time.LocalDateTime;

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
@Table(name = "producto_promocion")
public class ProductoPromocion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_producto_promocion")
    @SequenceGenerator(name = "seq_producto_promocion", sequenceName = "seq_producto_promocion", allocationSize = 1)
    @Column(name = "prod_prom_id")
    private Integer id;

    @Column(name = "prod_prom_fecha")
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn (name = "prod_prom_id_producto")
    private Producto producto; 

    @ManyToOne
    @JoinColumn (name = "prod_prom_id_promocion")
    private Promocion promocion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

}
