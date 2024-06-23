package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.IPreguntaFrecuenteRepository;
import com.software.kefa.repository.modelo.PreguntaFrecuente;

import jakarta.transaction.Transactional;

@Service
public class PreguntaFrecuentreServiceImpl implements IPreguntaFrecuenteService {

    @Autowired
    private IPreguntaFrecuenteRepository preguntaFrecuenteRepository;

    @Override
    @Transactional (value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(PreguntaFrecuente preguntaFrecuente) {
        preguntaFrecuente.setCategoria(preguntaFrecuente.getCategoria());
        preguntaFrecuente.setPregunta(preguntaFrecuente.getPregunta());
        preguntaFrecuente.setRespuesta(preguntaFrecuente.getRespuesta());
        preguntaFrecuente.setFecha_creacion(LocalDateTime.now());
        this.preguntaFrecuenteRepository.insertar(preguntaFrecuente);
    }

    @Override
    @Transactional (value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(PreguntaFrecuente preguntaFrecuente) {
        this.preguntaFrecuenteRepository.actualizar(preguntaFrecuente);
    }

    @Override
    @Transactional (value = Transactional.TxType.REQUIRES_NEW)
    public List<PreguntaFrecuente> buscarTodo() {
        return this.preguntaFrecuenteRepository.seleccionarTodo();
    }

    @Override
    @Transactional (value = Transactional.TxType.REQUIRES_NEW)
    public PreguntaFrecuente buscarPorId(Integer id) {
        return this.preguntaFrecuenteRepository.seleccionarPorId(id);
    }

}
