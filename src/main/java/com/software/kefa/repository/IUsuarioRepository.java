package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.repository.modelo.modelosdto.UsuarioPerfilDTO;

public interface IUsuarioRepository {
    public void insertar(Usuario usuario);
    public void actualizar (Usuario usuario);
    public Usuario seleccionarPorCedula (String cedula);
    public Usuario seleccionarPorNickname (String nickname);
    public Usuario seleccionarPorContrasenia (String contrasenia);
    public Usuario seleccionarPorEmail (String email);
    public UsuarioPerfilDTO seleccionarInformacion (String nickname);
}
