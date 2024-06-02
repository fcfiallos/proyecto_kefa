package com.software.kefa.repository;

import java.util.List;

import com.software.kefa.repository.modelo.Cliente;
import com.software.kefa.repository.modelo.modelosdto.ClienteDTO;

public interface IClienteRepository {
    public void insertar (Cliente cliente);
    public void actualizar (Cliente cliente);
    public Cliente seleccionar (String cedula);
    public List<ClienteDTO> seleccionarTodo ();
}
