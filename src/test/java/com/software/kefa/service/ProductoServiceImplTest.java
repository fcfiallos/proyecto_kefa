package com.software.kefa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.software.kefa.service.modelosto.ProductoTO;

@SpringBootTest
public class ProductoServiceImplTest {
    @Autowired
    private ProductoServiceImpl productoServiceImpl;

    @Test
    void testGuardar() {
        ProductoTO producto = new ProductoTO();
        producto.setNombre("Producto 1");
        producto.setDescripcion("Descripcion 1");
        producto.setCodigo("123456");
        producto.setNombreProveedor("Proveedor 1");
        producto.setCantidad(10);
        producto.setPrecio("1000.0");
        producto.setImagen("https://th.bing.com/th/id/OIG3.heCatriYvJLkSNCdjzTj?w=1024&h=1024&rs=1&pid=ImgDetMain");
        producto.setTipo("Producto");
        producto.setPais("Colombia");
        productoServiceImpl.guardar(producto);

    }
}
