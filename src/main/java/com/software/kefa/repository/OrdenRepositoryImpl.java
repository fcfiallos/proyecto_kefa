package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Orden;
import com.software.kefa.repository.modelo.modelosdto.OrdenDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class OrdenRepositoryImpl implements IOrdenRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional (value = Transactional.TxType.MANDATORY)
    public void insertar(Orden orden) {
        this.entityManager.persist(orden);
    }

    @Override
    @Transactional (value = Transactional.TxType.MANDATORY)
    public void actualizar(Orden orden) {
        this.entityManager.merge(orden);
    }

    @Override
    @Transactional (value = Transactional.TxType.NOT_SUPPORTED)
    public Orden seleccionarPorCodigo(String codigo) {
        TypedQuery<Orden> myQuery = this.entityManager.createQuery("SELECT o FROM Orden o WHERE o.codigo = :codigo", Orden.class);
        myQuery.setParameter("codigo", codigo);
        return myQuery.getSingleResult();
    }

    @Override
    @Transactional (value = Transactional.TxType.NOT_SUPPORTED)
    public List<OrdenDTO> seleccionarTodos() {
        return null;
    }

    @Override
    @Transactional (value = Transactional.TxType.NOT_SUPPORTED)
    public Orden seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(Orden.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional (value = Transactional.TxType.NOT_SUPPORTED)
    public Orden seleccionarTodo() {
        try {
            return this.entityManager.createQuery("SELECT o FROM Orden o", Orden.class).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
