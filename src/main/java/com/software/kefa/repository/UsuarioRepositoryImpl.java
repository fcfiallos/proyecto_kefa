package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.repository.modelo.modelosdto.UsuarioPerfilDTO;

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
    public Usuario seleccionarPorCedula(String cedula) {
        TypedQuery<Usuario> query = this.entityManager.createQuery("SELECT u FROM Usuario u WHERE u.cedula= :cedula", Usuario.class);
        query.setParameter("cedula", cedula);
        return query.getSingleResult();
    }

    @Override
    public Usuario seleccionarPorNickname(String nickname) {
        TypedQuery<Usuario> query = this.entityManager.createQuery("SELECT u FROM Usuario u WHERE u.nickname= :nickname", Usuario.class);
        query.setParameter("nickname", nickname);
        return query.getSingleResult();
    }

    @Override
    public Usuario seleccionarPorContrasenia(String contrasenia) {
        TypedQuery<Usuario> query = this.entityManager.createQuery("SELECT u FROM Usuario u WHERE u.contrasenia= :contrasenia", Usuario.class);
        query.setParameter("contrasenia", contrasenia);
        return query.getSingleResult();
    }

    @Override
    public UsuarioPerfilDTO seleccionarInformacion(String nickname) {
       return null;
    }

}
