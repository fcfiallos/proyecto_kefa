package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.software.kefa.excepcion.MensajeExisteExcepcion;
import com.software.kefa.repository.IRegistroSesionRepository;
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.RegistroSesion;
import com.software.kefa.repository.modelo.Rol;
import com.software.kefa.repository.modelo.Ubicacion;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.repository.modelo.modelosdto.UsuarioPerfilDTO;
import com.software.kefa.service.modelosto.UsuarioRegistroTO;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

/**
 * This class implements the {@link IUsuarioService} interface and provides the
 * implementation
 * for the methods defined in the interface. It is responsible for handling
 * user-related operations
 * such as user registration, updating user information, and checking user
 * existence.
 */
@Service
public class UsuarioServiceImple implements IUsuarioService {
    @Autowired
    private IUsuarioRepository repositoryImpl;

    @Autowired
    private IRegistroSesionRepository registroSesionRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Saves a new user in the system.
     *
     * @param usuarioTO The user registration data.
     * @throws MensajeExisteExcepcion   If the user already exists.
     * @throws IllegalArgumentException If the passwords do not match.
     */
    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public void guardar(UsuarioRegistroTO usuarioTO) {
        String confirmar = usuarioTO.getConstrasenia();
        String recofirmar = usuarioTO.getConstraseniaRepetir();
        String cedula = usuarioTO.getCedula();
        String nickname = usuarioTO.getNickname();

        // Verificar si el usuario ya existe por cédula o nickname
        if (this.existeUsuarioCedula(cedula) || this.existeUsuarioNickname(nickname)) {
            throw new MensajeExisteExcepcion("El usuario ya existe");
        }

        if (!confirmar.equals(recofirmar)) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }
        Usuario usuario = new Usuario();
        usuario.setApellido(usuarioTO.getNombreYApellido());
        usuario.setCedula(usuarioTO.getCedula());

        // Encriptación de contraseña del usuario
        String passwordEncriptada = passwordEncoder.encode(usuarioTO.getConstrasenia());
        usuario.setConstrasenia(passwordEncriptada);

        usuario.setCorreoElectronico(usuarioTO.getCorreoElectronico());
        usuario.setGenero(usuarioTO.getGenero());
        usuario.setNickname(usuarioTO.getNickname());
        usuario.setNombre(usuarioTO.getNombreYApellido());

        // Encriptacion de Respuestas de Seguridad
        String encriptadaPreDos = passwordEncoder.encode(usuarioTO.getPreguntaDos());
        usuario.setPreguntaDos(encriptadaPreDos);
        String encriptadaPreTres = passwordEncoder.encode(usuarioTO.getPreguntaTres());
        usuario.setPreguntaTres(encriptadaPreTres);
        String encriptadaPreUno = passwordEncoder.encode(usuarioTO.getPreguntaUno());
        usuario.setPreguntaUno(encriptadaPreUno);

        usuario.setTelefono(usuarioTO.getTelefono());

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setCiudad(usuarioTO.getCiudad());
        ubicacion.setCodigoPostal(usuarioTO.getCodigoPostal());
        ubicacion.setDireccion(usuarioTO.getDireccion());
        ubicacion.setProvincia(usuarioTO.getProvincia());
        ubicacion.setUsuario(usuario);

        Rol rol = new Rol();
        rol.setNombre("Cliente");
        usuario.setRol(rol);

        usuario.setUbicacion(ubicacion);

        this.repositoryImpl.insertar(usuario);
    }

    /**
     * Actualiza un usuario en el sistema.
     *
     * @param usuario El objeto Usuario que se desea actualizar.
     */
    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public void actualizar(Usuario usuario) {
        this.repositoryImpl.actualizar(usuario);
    }

    /**
     * Registers a list of users efficiently.
     * 
     * @param usuarioEfi the list of users to be registered
     */
    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public void registroEficiente(List<UsuarioRegistroTO> usuarioEfi) {

        if (usuarioEfi != null) {
            usuarioEfi.parallelStream().forEach(this::guardar);
        } else {
            throw new IllegalArgumentException("La lista de usuarios no puede ser nula");
        }

    }

    /**
     * Checks if a user with the given cedula exists.
     *
     * @param cedula the cedula of the user to check
     * @return true if a user with the given cedula exists, false otherwise
     */
    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public boolean existeUsuarioCedula(String cedula) {
        Usuario usuario = this.repositoryImpl.seleccionarPorCedula(cedula);
        return usuario != null;
    }

    /**
        * Checks if a user with the given nickname exists.
        *
        * @param nickname the nickname of the user to check
        * @return true if a user with the given nickname exists, false otherwise
        */
    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public boolean existeUsuarioNickname(String nickname) {
        Usuario usuario = this.repositoryImpl.seleccionarPorNickname(nickname);
        return usuario != null;
    }

    /**
        * Busca un usuario por su nickname.
        *
        * @param nickname el nickname del usuario a buscar
        * @return el usuario encontrado, o null si no se encuentra ninguno con el nickname dado
        */
    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public Usuario buscarPorNickname(String nickname) {
        return this.repositoryImpl.seleccionarPorNickname(nickname);
    }

    /**
     * Busca la información de un usuario por su nickname.
     *
     * @param nickname el nickname del usuario a buscar
     * @return un objeto UsuarioPerfilDTO que contiene la información del usuario
     */
    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public UsuarioPerfilDTO buscarInformacion(String nickname) {
        return this.repositoryImpl.seleccionarInformacion(nickname);
    }

    /**
     * Inicia sesión de un usuario con el nickname y contraseña proporcionados.
     * 
     * @param nickname    El nickname del usuario.
     * @param contrasenia La contraseña del usuario.
     * @throws IllegalArgumentException Si el usuario no existe o la contraseña es
     *                                  incorrecta.
     */
    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public void iniciarSesion(String nickname, String contrasenia) {
        if (!existeUsuarioNickname(nickname)) {
            throw new IllegalArgumentException("El usuario no existe");
        }
        Usuario usuario = buscarPorNickname(nickname);
        if (!passwordEncoder.matches(contrasenia, usuario.getConstrasenia())) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }
        // Aquí se registra el inicio de sesión en IRegistroSesionRepository
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setUsuario(this.buscarPorNickname(nickname));
        registroSesion.setFechaInicio(LocalDateTime.now());
        registroSesion.setEstado("Activo");
        registroSesionRepository.insertar(registroSesion);
    }

    /**
     * Closes the session for the given user.
     * 
     * @param nickname the nickname of the user
     */
    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public void cerrarSesion(String nickname) {
        RegistroSesion registroSesion = registroSesionRepository.seleccionarPorNickname(nickname);
        if (registroSesion != null) {
            registroSesion.setFechaFin(LocalDateTime.now());
            registroSesion.setEstado("Inactivo");
            registroSesionRepository.actualizar(registroSesion);
        } else {
            throw new IllegalArgumentException("No se puede cerrar la sesión de un usuario que no ha iniciado sesión");
        }

    }

    /**
        * Busca un usuario por su dirección de correo electrónico.
        *
        * @param email la dirección de correo electrónico del usuario a buscar
        * @return el usuario encontrado, o null si no se encuentra ninguno con el correo electrónico especificado
        */
    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public Usuario buscarPorEmail(String email) {
        return this.repositoryImpl.seleccionarPorEmail(email);
    }

    /**
     * Validates the password recovery process for a given email and user information.
     *
     * @param email The email of the user.
     * @param usuarioTO The user information containing the answers to security questions.
     * @return True if the answers to the security questions are correct, false otherwise.
     * @throws MensajeExisteExcepcion If the user email does not exist.
     * @throws IllegalArgumentException If any of the answers to the security questions is incorrect.
     */
    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public boolean validarRecuperarContrasenia(String email, UsuarioRegistroTO usuarioTO) {
        Usuario usuario = this.buscarPorEmail(email);

        if (usuario == null) {
            throw new IllegalArgumentException("El email del usuario no existe");
        }
        if (!passwordEncoder.matches(usuarioTO.getPreguntaUno(), usuario.getPreguntaUno())) {
            throw new IllegalArgumentException("La respuesta a la pregunta de seguridad 1 es incorrecta");
        }
        if (!passwordEncoder.matches(usuarioTO.getPreguntaDos(), usuario.getPreguntaDos())) {
            throw new IllegalArgumentException("La respuesta a la pregunta de seguridad 2 es incorrecta");
        }
        if (!passwordEncoder.matches(usuarioTO.getPreguntaTres(), usuario.getPreguntaTres())) {
            throw new IllegalArgumentException("La respuesta a la pregunta de seguridad 3 es incorrecta");
        }

        return true;
    }

    /**
     * Recovers the password for a user with the given email.
     * 
     * @param email The email of the user.
     * @param usuarioTO The UsuarioRegistroTO object containing the new password.
     * @throws MensajeExisteExcepcion If the email does not exist.
     * @throws IllegalArgumentException If the passwords do not match.
     * @throws MensajeExisteExcepcion If the password update fails.
     */
    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public void recuperarContrasenia(String email, UsuarioRegistroTO usuarioTO) {
        Usuario usuario = this.buscarPorEmail(email);
        if (usuario == null) {
            throw new MensajeExisteExcepcion("El email del usuario no existe");
        }

        if (!usuarioTO.getConstrasenia().equals(usuarioTO.getConstraseniaRepetir())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        try {
                usuario.setConstrasenia(passwordEncoder.encode(usuarioTO.getConstrasenia()));
                this.actualizar(usuario);
        } catch (MensajeExisteExcepcion e) {
            throw new MensajeExisteExcepcion("No se pudo actualizar la contraseña");
        }

    }
}
