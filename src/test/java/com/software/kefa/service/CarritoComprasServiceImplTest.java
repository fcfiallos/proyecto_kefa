package com.software.kefa.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CarritoComprasServiceImplTest {

    @Autowired
    private CarritoComprasServiceImpl carritoComprasService;
    
    @Test
    void testAgregarProductoAlCarrito() {
        assertThrows(RuntimeException.class, () -> {
        carritoComprasService.agregarProductoAlCarrito(1, 10);
    }, "Carrito de Compras o Producto no encontrado");
    }
}
