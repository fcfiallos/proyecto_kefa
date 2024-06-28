package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.IPreguntaFrecuenteRepository;
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.PreguntaFrecuente;
import com.software.kefa.repository.modelo.Usuario;

import jakarta.transaction.Transactional;

@Service
public class PreguntaFrecuentreServiceImpl implements IPreguntaFrecuenteService {

    @Autowired
    private IPreguntaFrecuenteRepository preguntaFrecuenteRepository;

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Override
    @Transactional (value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(PreguntaFrecuente preguntaFrecuente, String nickname) {
        Usuario usuario = this.iUsuarioRepository.seleccionarPorNickname(nickname);
        preguntaFrecuente.setCategoria(preguntaFrecuente.getCategoria());
        preguntaFrecuente.setPregunta(preguntaFrecuente.getPregunta());
        preguntaFrecuente.setRespuesta(preguntaFrecuente.getRespuesta());
        preguntaFrecuente.setFecha_creacion(LocalDateTime.now());
        preguntaFrecuente.setUsuario(usuario);
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
