package com.software.kefa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.software.kefa.excepcion.UsuarioExisteExcepcion;
import com.software.kefa.repository.IRolRepository;
import com.software.kefa.repository.IUsuarioRepository;
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
    private IRolRepository rolRepositoryImpl;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    
    /**
     * Saves a new user in the system.
     *
     * @param usuarioTO the user registration data.
     * @throws UsuarioExisteExcepcion if the user already exists.
     * @throws IllegalArgumentException if the passwords do not match.
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
            throw new UsuarioExisteExcepcion("El usuario ya existe");
        }

        if (!confirmar.equals(recofirmar)) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }
        Usuario usuario = new Usuario();
        usuario.setApellido(usuarioTO.getApellido());
        usuario.setCedula(usuarioTO.getCedula());

        String passwordEncriptada = passwordEncoder.encode(usuarioTO.getConstrasenia());
        usuario.setConstrasenia(passwordEncriptada);
        
        //usuario.setConstrasenia(usuarioTO.getConstrasenia()); 
        usuario.setCorreoElectronico(usuarioTO.getCorreoElectronico());
        usuario.setGenero(usuarioTO.getGenero());
        usuario.setNickname(usuarioTO.getNickname());
        usuario.setNombre(usuarioTO.getNombre());
        usuario.setPreguntaDos(usuarioTO.getPreguntaDos());
        usuario.setPreguntaTres(usuarioTO.getPreguntaTres());
        usuario.setPreguntaUno(usuarioTO.getPreguntaUno());
        usuario.setTelefono(usuarioTO.getTelefono());

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setCiudad(usuarioTO.getCiudad());
        ubicacion.setCodigoPostal(usuarioTO.getCodigoPostal());
        ubicacion.setDireccion(usuarioTO.getDireccion());
        ubicacion.setProvincia(usuarioTO.getProvincia());
        ubicacion.setUsuario(usuario);

        Rol rol = new Rol();
        rol.setNombre(usuarioTO.getRol());
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
    public boolean existeUsuarioNickname(String nickname) {
        Usuario usuario = this.repositoryImpl.seleccionarPorNickname(nickname);
        return usuario != null;
    }

    @Override
    public Usuario buscarPorNickname(String nickname) {
        return this.repositoryImpl.seleccionarPorNickname(nickname);
    }

    @Override
    public UsuarioPerfilDTO buscarInformacion(String nickname) {
        return this.repositoryImpl.seleccionarInformacion(nickname);
    }

}
