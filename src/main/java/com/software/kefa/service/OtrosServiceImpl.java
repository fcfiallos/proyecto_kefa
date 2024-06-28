package com.software.kefa.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.IComentarioRepository;
import com.software.kefa.repository.IDevolucionesRepository;
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.Comentario;
import com.software.kefa.repository.modelo.Devolucion;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.service.modelosto.OtrosTO;

import jakarta.transaction.Transactional;

@Service
public class OtrosServiceImpl implements IOtrosService {

    @Autowired
    private IDevolucionesRepository devolucionesRepository;
    @Autowired
    private IComentarioRepository comentarioRepository;
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardarComentario(OtrosTO otrosTO, String nickname) {
        Comentario comentario = new Comentario();
        Usuario usuario = this.usuarioRepository.seleccionarPorNickname(nickname);
        comentario.setComentario(otrosTO.getComentario());
        comentario.setFecha_publicacion(LocalDateTime.now());
        comentario.setUsuario(usuario);
        this.comentarioRepository.insertar(comentario);

    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardarDevolucion(OtrosTO otrosTO, String nickname) {
        Devolucion devolucion = new Devolucion();
        Usuario usuario= this.usuarioRepository.seleccionarPorNickname(nickname);
        devolucion.setMotivo(otrosTO.getMotivoDevolucion());
        devolucion.setEstado_del_producto(otrosTO.getEstadoDevolucion());
        devolucion.setFecha_devolucion(LocalDateTime.now());
        devolucion.setUsuario(usuario);
        this.devolucionesRepository.insertar(devolucion);
    }

}
