package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Rol;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Repository
@Transactional
public class RolRepositoryImpl implements IRolRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void insertar(Rol rol) {
        this.entityManager.persist(rol);
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Rol seleccionarPorNombre(String nombre) {
        TypedQuery<Rol> query = this.entityManager.createQuery("SELECT r FROM Rol r WHERE r.nombre= :nombre",
                Rol.class);
        query.setParameter("nombre", nombre);
        List<Rol> roles = query.getResultList();
        if (roles.isEmpty()) {
            return null;
        } else {
            return roles.get(0);
        }
    }

}
