package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Notificacion;

public interface INotificacionRepository {
    public void insertar (Notificacion notificacion);
    public void actualizar (Notificacion notificacion);
    public void eliminar (Notificacion notificacion);
    public Notificacion seleccionarPorId (Integer id);

}
