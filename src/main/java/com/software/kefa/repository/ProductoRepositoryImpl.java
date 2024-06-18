package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.repository.modelo.modelosdto.ProductoDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Repository
@Transactional
public class ProductoRepositoryImpl implements IProductoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Producto> seleccionarTodo() {
        // TODO Auto-generated method stub
        return this.entityManager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }

    @Override
    public void insertar(Producto producto) {
        // TODO Auto-generated method stub
        this.entityManager.persist(producto);
    }

    @Override
    public void actualizar(Producto producto) {
        // TODO Auto-generated method stub
        this.entityManager.merge(producto);
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Producto seleccionarPorCodigo(String codigo) {
        // TODO Auto-generated method stub
        try {
            TypedQuery<Producto> query = this.entityManager
                    .createQuery("SELECT p FROM Producto p WHERE p.codigo= :codigo", Producto.class);
            query.setParameter("codigo", codigo);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}