package com.software.kefa.repository;

import org.springframework.stereotype.Repository;
 
import com.software.kefa.repository.modelo.Proveedor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
@Repository
@Transactional
public class ProveedorRepositoryImpl implements IProveedorRepository{
 
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    @Transactional(value = TxType.MANDATORY)
    public void insertar(Proveedor proveedor) {
        this.entityManager.persist(proveedor);
    }
 
    @Override
    @Transactional(value = TxType.MANDATORY)
    public void actualizar(Proveedor proveedor) {
        // TODO Auto-generated method stub
        this.entityManager.merge(proveedor);
    }
 
    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Proveedor seleccionarPorNombre(String nombre) {
        // TODO Auto-generated method stub
        try {
            TypedQuery<Proveedor> query = this.entityManager.createQuery("SELECT p FROM Proveedor p WHERE p.nombre= :nombre", Proveedor.class);
        query.setParameter("nombre", nombre);
        return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
 
}
