package com.software.kefa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.IRolRepository;
import com.software.kefa.repository.modelo.Rol;

import jakarta.transaction.Transactional;

@Service
public class RolServiceImpl implements IRolService {
    @Autowired
    private IRolRepository rolRepository;

    @Override
    @Transactional (value = Transactional.TxType.REQUIRES_NEW)
    public Rol buscarPorNickname(String nickname) {
        return this.rolRepository.seleccionarPorNickname(nickname);
    }


}
