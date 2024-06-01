package com.software.kefa.repository.modelo;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "prod_estado")
    private String estado;

    private Proveedor proveedor;
}
