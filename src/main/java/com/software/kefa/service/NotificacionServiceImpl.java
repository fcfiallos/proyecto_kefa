package com.software.kefa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.INotificacionRepository;
import com.software.kefa.repository.modelo.Notificacion;

import jakarta.transaction.Transactional;

@Service
public class NotificacionServiceImpl implements INotificacionService {
    @Autowired
    private INotificacionRepository notificacionRepository;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(Notificacion notificacion) {
        this.notificacionRepository.insertar(notificacion);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(Notificacion notificacion) {
        this.notificacionRepository.actualizar(notificacion);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Notificacion buscarPorId(Integer id) {
        return this.notificacionRepository.seleccionarPorId(id);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<Notificacion> buscarTodo() {
        return this.notificacionRepository.seleccionarTodo();
    }

}
