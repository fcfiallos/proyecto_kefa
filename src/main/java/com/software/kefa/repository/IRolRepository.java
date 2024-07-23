package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Rol;

public interface IRolRepository {
    public void insertar (Rol rol);
    public Rol seleccionarPorNickname(String nickname);
}
