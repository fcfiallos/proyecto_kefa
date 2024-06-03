package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Repository
@Transactional
public class UsuarioRepositoryImpl implements IUsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional (value = TxType.REQUIRED)
    public void insertar(Usuario usuario) {
        this.entityManager.persist(usuario);
    }
    
    @Override
    @Transactional (value = TxType.MANDATORY)
    public void actualizar(Usuario usuario) {
        this.entityManager.merge(usuario);
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Usuario seleccionar(String cedula) {
        TypedQuery<Usuario> query = this.entityManager.createQuery("SELECT u FROM Usuario u WHERE u.cedula= :cedula", Usuario.class);
        query.setParameter("cedula", cedula);
        return query.getSingleResult();
    }

}
