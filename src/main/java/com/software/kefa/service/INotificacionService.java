package com.software.kefa.service;

import java.util.List;

import com.software.kefa.repository.modelo.Notificacion;

public interface INotificacionService {
    public void guardar (Notificacion notificacion);
    public void actualizar (Notificacion notificacion);
    public Notificacion buscarPorId (Integer id);

    public List<Notificacion> buscarTodoPorNickname(String nickname);

}
