package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.ListaDeseos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ListaDeseoRepositoryImpl implements IListaDeseoRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(ListaDeseos listaDeseo) {
        this.entityManager.persist(listaDeseo);
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(ListaDeseos listaDeseo) {
        this.entityManager.merge(listaDeseo);
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public List<ListaDeseos> seleccionarTodo() {
        return this.entityManager.createQuery("SELECT l FROM ListaDeseos l", ListaDeseos.class).getResultList();
    }

}
