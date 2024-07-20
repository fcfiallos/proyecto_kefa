package com.software.kefa.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.IComentarioRepository;
import com.software.kefa.repository.IDevolucionesRepository;
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.Comentario;
import com.software.kefa.repository.modelo.Devoluciones;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.service.modelosto.OtrosTO;

import jakarta.transaction.Transactional;

/**
 * This class implements the IOtrosService interface and provides the
 * implementation for the service methods.
 * It is responsible for saving comments and returns in the system.
 */
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
    public void guardarComentario(OtrosTO comentario, String nickname) {
        Usuario usuario = this.usuarioRepository.seleccionarPorNickname(nickname);
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no existe");
        }

        if (comentario == null) {
            comentario = new OtrosTO();
            Comentario comen = new Comentario();
            comen.setComentario(comentario.getComentario());
            comen.setFechaPublicacion(LocalDateTime.now());
            comen.setUsuario(usuario);
            this.comentarioRepository.insertar(comen);
        }

    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardarDevolucion(OtrosTO devolucion, String nickname) {
        Usuario usuario = this.usuarioRepository.seleccionarPorNickname(nickname);
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no existe");
        }
        if (devolucion == null) {
            devolucion = new OtrosTO();
            Devoluciones devol = new Devoluciones();
            devol.setMotivo(devolucion.getMotivoDevolucion());
            devol.setEstado_del_producto(devolucion.getEstadoDevolucion());
            devol.setFechaDevolucion(LocalDateTime.now());
            devol.setUsuario(usuario);
            this.devolucionesRepository.insertar(devol);
        }
    }

}
