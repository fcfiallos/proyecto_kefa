package com.software.kefa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.IClienteRepository;
import com.software.kefa.repository.modelo.Cliente;
import com.software.kefa.repository.modelo.modelosdto.ClienteDTO;
import com.software.kefa.service.modelosto.UsuarioRegistroTO;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class ClienteServiceImpl implements IClienteService {
    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    @Transactional (value = TxType.REQUIRES_NEW)
    public void guardar(UsuarioRegistroTO registroTO) {
        Cliente cliente = new Cliente();
        
        cliente.setApellido(registroTO.getApellido());
        cliente.setCedula(registroTO.getCedula());
        cliente.setCorreoElectronico(registroTO.getCorreoElectronico());
        cliente.setGenero(registroTO.getGenero());
        cliente.setNombre(registroTO.getNombre());
        cliente.setTelefono(registroTO.getTelefono());

        this.clienteRepository.insertar(cliente);
    }

    @Override
    @Transactional (value = TxType.REQUIRES_NEW)
    public void actualizar(Cliente cliente) {
        this.clienteRepository.actualizar(cliente);
    }

    @Override
    @Transactional (value = TxType.REQUIRES_NEW)
    public Cliente buscar(String cedula) {
        return this.clienteRepository.seleccionar(cedula);
    }

    @Override
    @Transactional (value = TxType.REQUIRES_NEW)
    public List<ClienteDTO> reportar() {
        return this.clienteRepository.seleccionarTodo(); 
    }

    @Override
    @Transactional (value = TxType.REQUIRES_NEW)
    public void guadarEfic(List<UsuarioRegistroTO> registroTO) {
        registroTO.parallelStream().forEach(usuarioTO -> this.guardar((UsuarioRegistroTO) registroTO));
    }


}
