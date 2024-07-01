package com.software.kefa.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.software.kefa.repository.modelo.Orden;

import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class OrdenServiceImplTest {
    @Autowired
    private IOrdenService ordenService;

    @Test
    @Transactional
    void testCrearOrdenDePago() {
        // Arrange
        String nickname = "fcfch";
        Integer carritoId = 2;

        // Act
        Orden orden = ordenService.crearOrdenDePago(nickname, carritoId);

        // Assert
        assertNotNull(orden);
    }
}
