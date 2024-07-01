package com.software.kefa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CarritoCompraRepositoryImplTest {
    @Autowired
    private CarritoCompraRepositoryImpl carritoCompraRepositoryImpl;
    @Test
    void testSeleccionarDetalleOrdenPorCarritoCompraId() {
        // Arrange
        Integer id = 1;
        // Act
        carritoCompraRepositoryImpl.seleccionarDetalleOrdenPorCarritoCompraId(id);
        // Assert
    }
}
