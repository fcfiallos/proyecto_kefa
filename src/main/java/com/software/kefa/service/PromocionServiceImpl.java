package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.IPromocionRepository;
import com.software.kefa.repository.modelo.Promocion;

import jakarta.transaction.Transactional;

@Service
public class PromocionServiceImpl implements IPromocionService{
    @Autowired
    private IPromocionRepository promocionRepository;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(Promocion promocion) {
        this.promocionRepository.insertar(promocion);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(Promocion promocion) {
        this.promocionRepository.actualizar(promocion);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Promocion buscarPorId(Integer id) {
        return this.promocionRepository.seleccionarPorId(id);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Promocion buscarPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return this.promocionRepository.seleccionarPorFechas(fechaInicio, fechaFin);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<Promocion> buscarTodo() {
        return this.promocionRepository.seleccionarTodo();
    }

}
