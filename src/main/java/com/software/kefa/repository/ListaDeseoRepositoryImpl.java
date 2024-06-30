package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.ListaDeseos;
import com.software.kefa.repository.modelo.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ListaDeseoRepositoryImpl implements IListaDeseoRepository {
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
    public List<Producto> seleccionarTodo(Integer id) {
        try {
            return this.entityManager.createQuery("SELECT p FROM Producto p JOIN p.listaDeseos l WHERE l.id = :id", Producto.class)
                    .setParameter("id", id).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public ListaDeseos seleccionarPorId(Integer id) {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("El ID proporcionado es inválido: " + id);
            }
            return this.entityManager.find(ListaDeseos.class, id);
        } catch (NoResultException e) {
            return null;
        }

    }

}
