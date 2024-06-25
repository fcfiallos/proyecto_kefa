package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Producto;

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
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Producto> seleccionarPorCategoriaId(Integer categoriaID) {
        try {
            return this.entityManager.createQuery("SELECT p FROM Producto p WHERE p.categoriaProducto.id = :categoriaID",
                Producto.class).setParameter("categoriaID", categoriaID).getResultList();
        } catch (NoResultException e) {
            return null;
        }
        
    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void insertar(Producto producto) {
        this.entityManager.persist(producto);
    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void actualizar(Producto producto) {
        this.entityManager.merge(producto);
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Producto seleccionarPorCodigo(String codigo) {
        try {
            TypedQuery<Producto> query = this.entityManager
                    .createQuery("SELECT p FROM Producto p WHERE p.codigo= :codigo", Producto.class);
            query.setParameter("codigo", codigo);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Producto seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(Producto.class, id);
        } catch (NoResultException e) {
            return null;
        } 
    }

}