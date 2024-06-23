package com.software.kefa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.software.kefa.service.modelosto.OtrosTO;

@SpringBootTest
public class OtrosServiceImplTest {
    @Autowired
    private OtrosServiceImpl otrosServiceImpl;

    @Test
    void testGuardar() {
        OtrosTO otros = new OtrosTO();
        otros.setComentario("Comentario 1");
        otrosServiceImpl.guardarComentario(otros);
    }

    @Test
    void testGuardarDevolucion() {
        OtrosTO otros = new OtrosTO();
        otros.setEstadoDevolucion("Bueno");
        otros.setMotivoDevolucion("Motivo 1");
        otrosServiceImpl.guardarDevolucion(otros);
    }

}
