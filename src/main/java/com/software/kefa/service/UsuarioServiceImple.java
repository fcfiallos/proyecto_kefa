package com.software.kefa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.UsuarioRepositoryImpl;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.service.modelosto.UsuarioRegistroTO;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class UsuarioServiceImple implements IUsuarioService{
    @Autowired
    private UsuarioRepositoryImpl repositoryImpl;

    @Override
    @Transactional (value = TxType.REQUIRES_NEW)
    public void guardar(UsuarioRegistroTO usuarioTO) {
        Usuario usuario = new Usuario();

        usuario.setApellido(usuarioTO.getApellido());
        usuario.setCedula(usuarioTO.getCedula());
        usuario.setConstrasenia(usuarioTO.getConstrasenia());
        usuario.setCorreoElectronico(usuarioTO.getCorreoElectronico());
        usuario.setGenero(usuarioTO.getGenero());
        usuario.setNickname(usuarioTO.getNickname());
        usuario.setNombre(usuarioTO.getNombre());
        usuario.setPreguntaDos(usuarioTO.getPreguntaDos());
        usuario.setPreguntaTres(usuarioTO.getPreguntaTres());
        usuario.setPreguntaUno(usuarioTO.getPreguntaUno());
        usuario.setRol("Empleado");

        this.repositoryImpl.insertar(usuario);
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    @Transactional (value = TxType.REQUIRES_NEW)
    public void actualizar(Usuario usuario) {
        this.repositoryImpl.actualizar(usuario);
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    @Transactional (value = TxType.REQUIRES_NEW)
    public Usuario buscar(String cedula) {
        this.repositoryImpl.seleccionar(cedula);
        throw new UnsupportedOperationException("Unimplemented method 'buscar'");
    }

    @Override
    public void registroEficiente(List<UsuarioRegistroTO> usuarioEfi) {
        usuarioEfi.parallelStream().forEach(usuarioTO -> this.guardar((UsuarioRegistroTO) usuarioEfi));
        throw new UnsupportedOperationException("Unimplemented method 'registroEficiente'");
    }

}
