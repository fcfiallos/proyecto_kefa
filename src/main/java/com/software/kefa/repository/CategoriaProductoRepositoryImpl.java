package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.CategoriaProducto;
import com.software.kefa.repository.modelo.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Repository
@Transactional
public class CategoriaProductoRepositoryImpl implements ICategoriaProductoRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<CategoriaProducto> seleccionarTodo() {
        
        return this.entityManager.createQuery("SELECT c FROM CategoriaProducto c", CategoriaProducto.class).getResultList();
    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void insertar(CategoriaProducto producto) {
        
        this.entityManager.persist(producto);
    }

    @Override
    
    public void actualizar(CategoriaProducto producto) {
        
        this.entityManager.merge(producto);
    }

    @Override
    public CategoriaProducto seleccionarPorId(Integer id) {
        return this.entityManager.find(CategoriaProducto.class, id);
    }

    @Override
    public List<Producto> seleccionarProductosPorCategoria(Integer categoriaId) {
        return this.entityManager.createQuery("SELECT p FROM Producto p WHERE p.categoriaProducto.id = :categoriaId", Producto.class)
                .setParameter("categoriaId", categoriaId).getResultList();
    }

}
