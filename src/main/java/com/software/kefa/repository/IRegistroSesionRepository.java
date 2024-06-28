package com.software.kefa.repository;

import com.software.kefa.repository.modelo.RegistroSesion;

public interface IRegistroSesionRepository {
    public void insertar(RegistroSesion registroSesion);
    public void actualizar(RegistroSesion registroSesion);
    public RegistroSesion seleccionarPorId(Integer id);
    public RegistroSesion seleccionarPorNickname(String nickname);
}
