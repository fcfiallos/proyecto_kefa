package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.List;

import com.software.kefa.repository.modelo.Promocion;
import com.software.kefa.service.modelosto.PromocionTO;

public interface IPromocionService {
    public void guardar(PromocionTO promocion);

    public void actualizar(Promocion promocion);

    public Promocion buscarPorId(Integer id);

    public Promocion buscarPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public List<Promocion> buscarTodo();
}
