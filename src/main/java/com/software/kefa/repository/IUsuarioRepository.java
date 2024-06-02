package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Usuario;

public interface IUsuarioRepository {
    public void insertar(Usuario usuario);
    public void actualizar (Usuario usuario);
    public Usuario seleccionar (String cedula);

}
