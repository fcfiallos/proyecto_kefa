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
        OtrosTO comentario = new OtrosTO();
        comentario.setComentario("Comentario 1");
        otrosServiceImpl.guardarComentario(comentario, "fcfch");
    }

    @Test
    void testGuardarDevolucion() {
        OtrosTO devolucion = new OtrosTO();
        devolucion.setEstadoDevolucion("Bueno");
        devolucion.setMotivoDevolucion("Motivo 1");
        otrosServiceImpl.guardarDevolucion(devolucion, "fcfch");
    }

}
