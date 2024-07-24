package com.software.kefa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.IRolRepository;
import com.software.kefa.repository.modelo.Rol;

import jakarta.transaction.Transactional;



/**
    * Implementation of the IRolService interface that provides methods to work with roles.
    */
@Service
public class RolServiceImpl implements IRolService {
    @Autowired
    private IRolRepository rolRepository;

    /**
    * Busca un rol por su nickname.
    *
    * @param nickname el nickname del rol a buscar
    * @return el rol encontrado, o null si no se encuentra ninguno con el nickname dado
    */
    @Override
    @Transactional (value = Transactional.TxType.REQUIRES_NEW)
    public Rol buscarPorNickname(String nickname) {
        return this.rolRepository.seleccionarPorNickname(nickname);
    }


}
