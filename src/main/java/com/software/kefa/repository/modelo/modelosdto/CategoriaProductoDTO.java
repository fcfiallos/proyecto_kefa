package com.software.kefa.repository.modelo.modelosdto;

import java.util.List;

import com.software.kefa.repository.modelo.Producto;

public class CategoriaProductoDTO {
    private Integer id;
    private String tipo;
    private String descripcion;
    private List<Producto> productos;
    
    public CategoriaProductoDTO(Integer id, String tipo, String descripcion, List<Producto> productos) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.productos = productos;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    

}
