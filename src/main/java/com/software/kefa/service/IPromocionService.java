package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.List;

import com.software.kefa.repository.modelo.Promocion;

public interface IPromocionService {
    public void guardar(Promocion promocion);

    public void actualizar(Promocion promocion);

    public Promocion buscarPorId(Integer id);

    public Promocion buscarPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public List<Promocion> buscarTodo();
}
