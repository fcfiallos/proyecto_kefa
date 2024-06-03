package com.software.kefa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.excepcion.UsuarioExisteExcepcion;
import com.software.kefa.repository.UsuarioRepositoryImpl;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.service.modelosto.UsuarioRegistroTO;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class UsuarioServiceImple implements IUsuarioService {
    @Autowired
    private UsuarioRepositoryImpl repositoryImpl;

    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public void guardar(UsuarioRegistroTO usuarioTO) {
        String confirmar = usuarioTO.getConstrasenia();
        String recofirmar = usuarioTO.getConstraseniaRepetir();
        String cedula = usuarioTO.getCedula();
        String nickname = usuarioTO.getNickname();

        Usuario usuario = new Usuario();

        if (this.existeUsuario(cedula, nickname)) {
            throw new UsuarioExisteExcepcion("El usuario ya existe");
        }

        if (confirmar.equals(recofirmar) ) {
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
            usuario.setTelefono(usuarioTO.getTelefono());
            this.repositoryImpl.insertar(usuario);
        } else{
            System.out.println("Error no fue insertado el objeto o ya existe");
        }     
    }

    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public void actualizar(Usuario usuario) {
        this.repositoryImpl.actualizar(usuario);
    }

    @Override
    public void registroEficiente(List<UsuarioRegistroTO> usuarioEfi) {
        usuarioEfi.parallelStream().forEach(usuarioTO -> this.guardar((UsuarioRegistroTO) usuarioEfi));
    }

    @Override
    public boolean existeUsuario(String cedula, String nickname) {
        Usuario usuario = new Usuario();
        usuario= this.repositoryImpl.seleccionarPorCedula(cedula);
        usuario=this.repositoryImpl.seleccionarPorNickname(nickname);
        return usuario.getCedula() != cedula && usuario.getNickname() != nickname;
    }

}
