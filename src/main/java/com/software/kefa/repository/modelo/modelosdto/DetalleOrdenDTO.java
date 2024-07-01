package com.software.kefa.repository.modelo.modelosdto;

import java.math.BigDecimal;

public class DetalleOrdenDTO {
    private Integer cantidad;
    private BigDecimal precio;
    private String nombreProducto;

    public DetalleOrdenDTO() {
    }

    public DetalleOrdenDTO(Integer cantidad, BigDecimal precio, String nombreProducto) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.nombreProducto = nombreProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
}
