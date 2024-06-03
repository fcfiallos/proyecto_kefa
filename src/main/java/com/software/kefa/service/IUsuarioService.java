package com.software.kefa.service;

import java.util.List;

import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.service.modelosto.UsuarioRegistroTO;

public interface IUsuarioService {
    public void guardar(UsuarioRegistroTO usuarioTO);
    public void actualizar (Usuario usuario);
    public boolean existeUsuario (String cedula, String nickname);
    public void registroEficiente(List<UsuarioRegistroTO> usuarioEfi);

}
