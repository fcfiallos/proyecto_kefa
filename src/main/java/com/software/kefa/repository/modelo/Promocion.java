package com.software.kefa.repository.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "promocion")
public class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_promocion")
    @SequenceGenerator(name = "seq_promocion", sequenceName = "seq_promocion", allocationSize = 1)
    @Column(name = "prom_id")
    private Integer id;

    @Column(name = "prom_tipo_promocion")
    private String tipoPromocion;

    @Column(name = "prom_descuento")
    private BigDecimal descuento;

    @Column(name = "prom_fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "prom_fecha_fin")
    private LocalDateTime fechaFin;

    @Column(name = "prom_estado")
    private String estado;

    @ManyToMany(mappedBy = "promocion")
    private Set<Producto> producto;

}
