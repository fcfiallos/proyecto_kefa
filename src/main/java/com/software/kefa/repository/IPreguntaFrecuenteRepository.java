package com.software.kefa.repository;

import java.util.List;

import com.software.kefa.repository.modelo.PreguntaFrecuente;

public interface IPreguntaFrecuenteRepository {
    public void insertar(PreguntaFrecuente preguntaFrecuente);
    public void actualizar(PreguntaFrecuente preguntaFrecuente);
    public List<PreguntaFrecuente> seleccionarTodo();
}
