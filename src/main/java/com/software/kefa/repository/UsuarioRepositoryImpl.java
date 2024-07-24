package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.repository.modelo.modelosdto.UsuarioPerfilDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

/**
 * Implementation of the {@link IUsuarioRepository} interface that provides
 * methods for interacting with the Usuario entity in the database.
 */
@Repository
@Transactional
public class UsuarioRepositoryImpl implements IUsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Inserts a new Usuario entity into the database.
     *
     * @param usuario The Usuario object to be inserted.
     */
    @Override
    @Transactional(value = TxType.REQUIRED)
    public void insertar(Usuario usuario) {
        this.entityManager.persist(usuario);
    }

    /**
     * Updates an existing Usuario entity in the database.
     *
     * @param usuario The Usuario object to be updated.
     */
    @Override
    @Transactional(value = TxType.MANDATORY)
    public void actualizar(Usuario usuario) {
        this.entityManager.merge(usuario);
    }

    /**
     * Retrieves a Usuario entity from the database based on the provided cedula.
     *
     * @param cedula The cedula of the Usuario to be retrieved.
     * @return The Usuario object with the provided cedula, or null if not found.
     */
    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Usuario seleccionarPorCedula(String cedula) {
        try {
            TypedQuery<Usuario> query = this.entityManager
                    .createQuery("SELECT u FROM Usuario u WHERE u.cedula= :cedula", Usuario.class);
            query.setParameter("cedula", cedula);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Retrieves a Usuario entity from the database based on the provided nickname.
     *
     * @param nickname The nickname of the Usuario to be retrieved.
     * @return The Usuario object with the provided nickname, or null if not found.
     */
    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Usuario seleccionarPorNickname(String nickname) {
        try {
            TypedQuery<Usuario> query = this.entityManager
                    .createQuery("SELECT u FROM Usuario u WHERE u.nickname= :nickname", Usuario.class);
            query.setParameter("nickname", nickname);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Retrieves a Usuario entity from the database based on the provided contrasenia.
     *
     * @param contrasenia The contrasenia of the Usuario to be retrieved.
     * @return The Usuario object with the provided contrasenia.
     */
    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Usuario seleccionarPorContrasenia(String contrasenia) {
        TypedQuery<Usuario> query = this.entityManager
                .createQuery("SELECT u FROM Usuario u WHERE u.contrasenia= :contrasenia", Usuario.class);
        query.setParameter("contrasenia", contrasenia);
        return query.getSingleResult();
    }

    /**
     * Retrieves a UsuarioPerfilDTO object from the database based on the provided nickname.
     *
     * @param nickname The nickname of the Usuario to be retrieved.
     * @return The UsuarioPerfilDTO object with the provided nickname.
     */
    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public UsuarioPerfilDTO seleccionarInformacion(String nickname) {
        TypedQuery<UsuarioPerfilDTO> myQuery = this.entityManager.createQuery(
                "SELECT NEW com.software.kefa.repository.modelo.modelosdto.UsuarioPerfilDTO (u.nombre, u.apellido, u.cedula, u.correoElectronico, u.telefono, u.genero, u.ubicacion.codigoPostal, u.ubicacion.ciudad, u.ubicacion.provincia, u.ubicacion.direccion, u.nickname) FROM Usuario u WHERE u.nickname = :nickname",
                UsuarioPerfilDTO.class);
        myQuery.setParameter("nickname", nickname);
        return myQuery.getSingleResult();
    }

    /**
     * Retrieves a Usuario entity from the database based on the provided email.
     *
     * @param email The email of the Usuario to be retrieved.
     * @return The Usuario object with the provided email, or null if not found.
     */
    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Usuario seleccionarPorEmail(String email) {
        try {
            return this.entityManager.createQuery("SELECT u FROM Usuario u WHERE u.correoElectronico = :email",
                    Usuario.class).setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
