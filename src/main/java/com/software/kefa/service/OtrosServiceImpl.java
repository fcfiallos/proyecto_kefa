package com.software.kefa.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.IComentarioRepository;
import com.software.kefa.repository.IDevolucionesRepository;
import com.software.kefa.repository.modelo.Comentario;
import com.software.kefa.repository.modelo.Devolucion;
import com.software.kefa.service.modelosto.OtrosTO;

import jakarta.transaction.Transactional;

@Service
public class OtrosServiceImpl implements IOtrosService {

    @Autowired
    private IDevolucionesRepository devolucionesRepository;
    @Autowired
    private IComentarioRepository comentarioRepository;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardarComentario(OtrosTO otrosTO) {
        Comentario comentario = new Comentario();
        comentario.setComentario(otrosTO.getComentario());
        comentario.setFecha_publicacion(LocalDateTime.now());
        this.comentarioRepository.insertar(comentario);

    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardarDevolucion(OtrosTO otrosTO) {
        Devolucion devolucion = new Devolucion();
        devolucion.setMotivo(otrosTO.getMotivoDevolucion());
        devolucion.setEstado_del_producto(otrosTO.getEstadoDevolucion());
        devolucion.setFecha_devolucion(LocalDateTime.now());
        this.devolucionesRepository.insertar(devolucion);
    }

}
