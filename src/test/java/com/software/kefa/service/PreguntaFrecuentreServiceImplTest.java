package com.software.kefa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.software.kefa.repository.modelo.PreguntaFrecuente;

@SpringBootTest
public class PreguntaFrecuentreServiceImplTest {
    @Autowired
    private IPreguntaFrecuenteService preguntaFrecuenteService;
    
    @Test
    void testGuardar() {
        // Arrange
       /* PreguntaFrecuente preguntaFrecuente = new PreguntaFrecuente();
        preguntaFrecuente.setCategoria("Categoria");
        preguntaFrecuente.setPregunta("Pregunta");
        preguntaFrecuente.setRespuesta("Respuesta");
        // Act
        preguntaFrecuenteService.guardar(preguntaFrecuente, "fcfch");
        // Assert
        assertNotNull(preguntaFrecuente.getId());*/
    }

    @Test
    void testActualizar() {
        // Arrange
        PreguntaFrecuente preguntaFrecuente = this.preguntaFrecuenteService.buscarPorId(1);
        // Act
        preguntaFrecuente.setCategoria("Categoria 2");
        preguntaFrecuente.setPregunta("Pregunta 2");
        preguntaFrecuente.setRespuesta("Respuesta 2");
        preguntaFrecuenteService.actualizar(preguntaFrecuente);
        // Assert
        //PreguntaFrecuente preguntaFrecuenteActualizada = preguntaFrecuenteService.buscarPorId(preguntaFrecuente.getId());
        //assertNotNull(preguntaFrecuenteActualizada);
    }
}
