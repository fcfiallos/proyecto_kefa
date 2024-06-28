package com.software.kefa.service;

import java.util.List;

import com.software.kefa.repository.modelo.PreguntaFrecuente;

public interface IPreguntaFrecuenteService {
    public void guardar(PreguntaFrecuente preguntaFrecuente, String nickname);
    public void actualizar(PreguntaFrecuente preguntaFrecuente);
    public List<PreguntaFrecuente> buscarTodo();
    public PreguntaFrecuente buscarPorId(Integer id);
}
