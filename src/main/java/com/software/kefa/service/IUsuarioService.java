package com.software.kefa.service;

import java.util.List;

import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.repository.modelo.modelosdto.UsuarioPerfilDTO;
import com.software.kefa.service.modelosto.UsuarioRegistroTO;

public interface IUsuarioService {
    public void guardar(UsuarioRegistroTO usuarioTO);
    public void actualizar (Usuario usuario);
    public boolean existeUsuarioCedula (String cedula);
    public boolean existeUsuarioNickname(String nickname);
    public Usuario buscarPorNickname (String nickname);
    public void registroEficiente(List<UsuarioRegistroTO> usuarioEfi);
    public UsuarioPerfilDTO buscarInformacion(String nickname);
    public void iniciarSesion(String nickname, String contrasenia);
    public void cerrarSesion(String nickname);

}
