package com.software.kefa.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CarritoComprasServiceImplTest {

    @Autowired
    private CarritoCompraServiceImpl carritoComprasService;

    @Test
    void testAgregarProductoAlCarrito() {
        assertThrows(RuntimeException.class, () -> {
            carritoComprasService.agregarProductoAlCarrito(1, 1, "fcfch",1);
        }, "Carrito de Compras o Producto no encontrado");
    }
}
