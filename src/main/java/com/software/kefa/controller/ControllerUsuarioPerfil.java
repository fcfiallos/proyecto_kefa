package com.software.kefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.repository.modelo.Rol;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.repository.modelo.modelosdto.UsuarioPerfilDTO;
import com.software.kefa.service.IRolService;
import com.software.kefa.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kefa")
public class ControllerUsuarioPerfil {
    @Autowired
    private IUsuarioService iUsuarioService;

    @Autowired
    private IRolService rolService;

    /**
     * Retrieves the user profile form.
     * 
     * This method is responsible for displaying the user profile form. It retrieves the user's nickname from the session
     * and uses it to fetch the user's information. If the nickname is available, the user information is added to the model
     * and the "vista_perfil_usuario" view is returned. If there is no session or nickname available, the user is redirected
     * to the login form.
     * 
     * @param session the HttpSession object to retrieve the user's nickname from
     * @param model the Model object to add the user information to
     * @return the view name to render
     */
    @GetMapping("/perfil_usuario")
    public String mostrarFormularioUsuaClie(HttpSession session, Model model) {
        // Obtener el nickname de la sesión
        String nickname = (String) session.getAttribute("nickname");

        if (nickname != null) {
            UsuarioPerfilDTO usuario = this.iUsuarioService.buscarInformacion(nickname);

            Rol rol = this.rolService.buscarPorNickname(nickname);
    
            if (rol == null) {
                model.addAttribute("error", "El usuario no existe");
                return "formulario_inicio_sesion";
            }
    
            if (rol.getNombre().equals("Empleado")) {
                model.addAttribute("usuario", usuario);
                return "vista_perfil_usuario_empleado";
            }

            model.addAttribute("usuario", usuario);
            return "vista_perfil_usuario";
        } else {
            // Manejar el caso en que no hay una sesión o nickname disponible
            return "redirect:/kefa/formulario_iniciar_sesion";
        }
    }

    /**
     * Displays the form for updating user profile.
     * 
     * @param session the HttpSession object for accessing session attributes
     * @param model the Model object for adding attributes to the view
     * @return the name of the view to be rendered
     */
    @GetMapping("/formulario_actualizar_perfil/{nickname}")
    public String mostrarFormularioActualizarUsuaClie(HttpSession session, Model model) {
        // Obtener el nickname de la sesión
        String nickname = (String) session.getAttribute("nickname");

        if (nickname != null) {
            Usuario usuario = this.iUsuarioService.buscarPorNickname(nickname);
            model.addAttribute("usuario", usuario);
            return "formulario_actualizar_datos_Clie";
        } else {
            // Manejar el caso en que no hay una sesión o nickname disponible
            return "redirect:/kefa/formulario_iniciar_sesion";
        }
    }

    /**
     * Displays the form for updating user profile.
     *
     * @param session The HttpSession object for managing session data.
     * @param usuario The Usuario object containing the updated user profile data.
     * @param model   The Model object for adding attributes to the view.
     * @return The view name to be displayed after updating the user profile.
     */
    @PutMapping("/actualizar_perfil/{nickname}")
    public String mostrarFormularioActulizar(HttpSession session, Usuario usuario, Model model) {
        // Obtener el nickname de la sesión @PathVariable String nickname,
        String nickname = (String) session.getAttribute("nickname");

        if (nickname != null) {
            Usuario usuaAux = this.iUsuarioService.buscarPorNickname(nickname);

            if (usuaAux != null) {
                usuaAux.setApellido(usuario.getApellido());
                usuaAux.setNombre(usuario.getNombre());
                usuaAux.setCorreoElectronico(usuario.getCorreoElectronico());
                usuaAux.setTelefono(usuario.getTelefono());

                Rol rol = this.rolService.buscarPorNickname(nickname);

                if (rol == null) {
                    model.addAttribute("error", "El usuario no existe");
                    return "formulario_inicio_sesion";
                }

                if (rol.getNombre().equals("Empleado")) {
                    this.iUsuarioService.actualizar(usuaAux);
                    return "redirect:/kefa/lista_categoria_productos/empleado";
                }

                this.iUsuarioService.actualizar(usuaAux);
                return "redirect:/kefa/lista_categoria_productos";
            } else {
                model.addAttribute("error", "No se encontró el usuario");
                return "formulario_actualizar_datos_Clie";
            }
        } else {
            // Manejar el caso en que no hay una sesión o nickname disponible
            return "redirect:/kefa/formulario_iniciar_sesion";
        }
    }

}
