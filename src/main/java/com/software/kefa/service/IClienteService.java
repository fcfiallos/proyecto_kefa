package com.software.kefa.service;

import java.util.List;

import com.software.kefa.repository.modelo.Cliente;
import com.software.kefa.repository.modelo.modelosdto.ClienteDTO;
import com.software.kefa.service.modelosto.UsuarioRegistroTO;

public interface IClienteService {
    public void guardar (UsuarioRegistroTO registroTO);
    public void actualizar (Cliente cliente);
    public Cliente buscar (String cedula);
    public List<ClienteDTO> reportar ();
    public void guadarEfic(List<UsuarioRegistroTO> registroTO);
}
