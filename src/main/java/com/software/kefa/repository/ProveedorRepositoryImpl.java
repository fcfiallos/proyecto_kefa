package com.software.kefa.repository;

import javax.swing.text.html.parser.Entity;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Proveedor;
import com.software.kefa.repository.modelo.Usuario;

import jakarta.persistence.EntityManager;
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
            TypedQuery<Proveedor> query = this.entityManager.createQuery("SELECT u FROM Proveedor p WHERE p.nombre= :nombre", Proveedor.class);
        query.setParameter("nombre", nombre);
        return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        
    }

}
