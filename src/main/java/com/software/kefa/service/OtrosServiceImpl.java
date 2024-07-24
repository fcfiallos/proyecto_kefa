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
 * This class implements the IOtrosService interface and provides the implementation for the service methods.
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

    /**
     * Saves a comment for a user.
     *
     * @param comentario The comment to be saved.
     * @param nickname The nickname of the user.
     * @throws IllegalArgumentException If the user does not exist.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardarComentario(OtrosTO comentario, String nickname) {
        Usuario usuario = this.usuarioRepository.seleccionarPorNickname(nickname);
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no existe");
        }

        Comentario comen = new Comentario();
        comen.setComentario(comentario.getComentario());
        comen.setFechaPublicacion(LocalDateTime.now());
        comen.setUsuario(usuario);
        this.comentarioRepository.insertar(comen);

    }

    /**
     * Guarda una devolución en la base de datos.
     * 
     * @param devolucion el objeto OtrosTO que representa la devolución a guardar
     * @param nickname el nickname del usuario que realiza la devolución
     * @throws IllegalArgumentException si el usuario no existe
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardarDevolucion(OtrosTO devolucion, String nickname) {
        Usuario usuario = this.usuarioRepository.seleccionarPorNickname(nickname);
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no existe");
        }

        Devoluciones devol = new Devoluciones();
        devol.setMotivo(devolucion.getMotivoDevolucion());
        devol.setEstado_del_producto(devolucion.getEstadoDevolucion());
        devol.setFechaDevolucion(LocalDateTime.now());
        devol.setUsuario(usuario);
        this.devolucionesRepository.insertar(devol);

    }

}
