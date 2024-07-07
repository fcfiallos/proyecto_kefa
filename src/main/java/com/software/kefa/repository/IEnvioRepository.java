package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Envio;

public interface IEnvioRepository {
    public void insertar (Envio envio);
    public void actualizar (Envio envio);
    public void eliminar (Envio envio);
    public Envio seleccionarPorId (Integer id);
}
