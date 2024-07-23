package com.software.kefa.service;

import com.software.kefa.repository.modelo.Rol;

public interface IRolService {
    public Rol buscarPorNickname(String nickname);
}
