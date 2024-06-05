package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.CategoriaProducto;
import com.software.kefa.repository.modelo.modelosdto.CategoriaProductoDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Repository
@Transactional
public class CategoriaProductoRepositoryImpl implements ICategoriaProductoRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<CategoriaProductoDTO> seleccionarTodo() {
        // TODO Auto-generated method stub
        TypedQuery<CategoriaProductoDTO> myQ = this.entityManager.createNamedQuery("SELECT NEW com.software.kefa.repository.modelo.modelosdto.CategoriaProductoDTO (p.tipo, p.descripcion) FROM CategoriaProducto p", CategoriaProductoDTO.class);
        return myQ.getResultList();
    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void insertar(CategoriaProducto categoriaProducto) {
        // TODO Auto-generated method stub
        this.entityManager.persist(categoriaProducto);
    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void actualizar(CategoriaProducto categoriaProducto) {
        // TODO Auto-generated method stub
        this.entityManager.merge(categoriaProducto);
    }

}
