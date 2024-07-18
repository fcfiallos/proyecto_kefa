package com.software.kefa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CarritoComprasServiceImplTest {

    @Autowired
    private CarritoCompraServiceImpl carritoComprasService;

    @Test
    void testAgregarProductoAlCarrito() {
        carritoComprasService.agregarProductoAlCarrito(9, "fcfch", 1, null);
    }
}
