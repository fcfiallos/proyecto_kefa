package com.software.kefa.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.software.kefa.repository.modelo.Promocion;

public interface IPromocionRepository {
    public Promocion seleccionarPorId(Integer id);

    public void insertar(Promocion promocion);

    public void actualizar(Promocion promocion);

    public Promocion seleccionarPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public List<Promocion> seleccionarTodo();
}
