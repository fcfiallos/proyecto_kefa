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
        usuario.setApellido(usuarioTO.getApellido());
        usuario.setCedula(usuarioTO.getCedula());

        // Encriptación de contraseña del usuario
        String passwordEncriptada = passwordEncoder.encode(usuarioTO.getConstrasenia());
        usuario.setConstrasenia(passwordEncriptada);

        usuario.setCorreoElectronico(usuarioTO.getCorreoElectronico());
        usuario.setGenero(usuarioTO.getGenero());
        usuario.setNickname(usuarioTO.getNickname());
        usuario.setNombre(usuarioTO.getNombre());

        // Encriptacion de Preguntas de Seguridad
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
            System.out.println("no se puedo registrar usuario");
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

    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public boolean existeUsuarioNickname(String nickname) {
        Usuario usuario = this.repositoryImpl.seleccionarPorNickname(nickname);
        return usuario != null;
    }

    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public Usuario buscarPorNickname(String nickname) {
        return this.repositoryImpl.seleccionarPorNickname(nickname);
    }

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

}
