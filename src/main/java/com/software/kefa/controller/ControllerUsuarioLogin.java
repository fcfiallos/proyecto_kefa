package com.software.kefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.excepcion.MensajeExisteExcepcion;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.service.IUsuarioService;
import com.software.kefa.service.modelosto.UsuarioRegistroTO;

import jakarta.servlet.http.HttpSession;

/**
 * This class is a controller that handles user login and registration related
 * requests.
 * It contains methods for displaying login and registration forms, registering
 * a user or client,
 * initiating a user session, and closing a user session.
 */
@Controller
@RequestMapping("/kefa")
public class ControllerUsuarioLogin {
    @Autowired
    private IUsuarioService iUsuarioService;

    /**
     * Returns the name of the HTML file without the extension to display the login
     * form.
     *
     * @return the name of the HTML file for the login form
     */
    @GetMapping("/presentacion")
    public String mostrarPaginaDeLogin() {
        // Retorna el nombre del archivo HTML sin la extensión
        return "formulario_presentacion"; // Asume que tienes un archivo login.html en tu carpeta de recursos
    }

    /**
     * Displays the registration form for the user/client.
     *
     * @param model the model object to be used for rendering the view
     * @return the name of the view to be displayed (formulario_registro_ClieUsua)
     */
    @GetMapping("/formulario_registro")
    public String mostrarFormularioUsuaClie(Model model) {
        model.addAttribute("usuarioRegistroTO", new UsuarioRegistroTO());
        return "formulario_registro_ClieUsua";
    }

    /**
     * Registers a user or client by saving their information in the system.
     *
     * @param usuarioRegistroTO The object containing the user/client registration
     *                          information.
     * @param model             The model object used for adding attributes and
     *                          rendering views.
     * @return The view name to be displayed after the registration process.
     */
    @PostMapping("/registrar")
    public String registrarUsuaClie(@ModelAttribute("usuarioRegistroTO") UsuarioRegistroTO usuarioRegistroTO,
            Model model) {
        if (usuarioRegistroTO.getCedula().isEmpty() || usuarioRegistroTO.getCedula().length() > 10) {
            model.addAttribute("error", "La cédula no debe exceder los 10 caracteres");
            return "formulario_registro_ClieUsua";
        }

        // Validar otros campos
        if (usuarioRegistroTO.getConstrasenia().isEmpty() ||
                usuarioRegistroTO.getConstraseniaRepetir().isEmpty() ||
                usuarioRegistroTO.getCorreoElectronico().isEmpty() ||
                usuarioRegistroTO.getGenero().isEmpty() ||
                usuarioRegistroTO.getNickname().isEmpty() ||
                usuarioRegistroTO.getNombreYApellido().isEmpty() ||
                usuarioRegistroTO.getPreguntaDos().isEmpty() ||
                usuarioRegistroTO.getPreguntaTres().isEmpty() ||
                usuarioRegistroTO.getPreguntaUno().isEmpty() ||
                usuarioRegistroTO.getTelefono().isEmpty()) {
            model.addAttribute("error", "Todos los campos son obligatorios");
            return "formulario_registro_ClieUsua";
        }

        try {
            this.iUsuarioService.guardar(usuarioRegistroTO);
            return "redirect:/kefa/formulario_iniciar_sesion";
        } catch (MensajeExisteExcepcion e) {
            model.addAttribute("error", e.getMessage());
            return "formulario_registro_ClieUsua";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "formulario_registro_ClieUsua";
        }
    }

    /**
     * Displays the login form for the user.
     * 
     * @param model the model object to be used for rendering the view
     * @return the name of the view to be rendered
     */
    @GetMapping("/formulario_iniciar_sesion")
    public String mostrarFormularioIniciarSecion(Model model) {
        model.addAttribute("usuarioRegistroTO", new UsuarioRegistroTO());
        return "formulario_inicio_sesion";
    }

    /**
     * Handles the request to initiate a user session.
     * 
     * @param usuarioRegistroTO The user registration transfer object containing the
     *                          user's nickname and password.
     * @param model             The model object to add attributes for the view.
     * @param session           The HttpSession object to store the user's nickname.
     * @return A string representing the view to redirect to after successful
     *         session initiation.
     * @throws IllegalArgumentException If an invalid argument is passed or an error
     *                                  occurs during session initiation.
     */
    @PostMapping("/iniciarSesion")
    public String iniciarSesion(@ModelAttribute("usuarioRegistroTO") UsuarioRegistroTO usuarioRegistroTO, Model model,
            HttpSession session) {
        try {
            // Llamada al método iniciarSesion del servicio
            this.iUsuarioService.iniciarSesion(usuarioRegistroTO.getNickname(), usuarioRegistroTO.getConstrasenia());
            session.setAttribute("nickname", usuarioRegistroTO.getNickname()); // Guardar nickname en la sesión
            return "redirect:/kefa/lista_categoria_productos";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "formulario_inicio_sesion";
        }
    }

    /**
     * Closes the user session and redirects to the login page.
     * 
     * @param session the HttpSession object representing the user session
     * @return a String representing the redirect URL to the login page
     */
    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession session) {
        String nickname = (String) session.getAttribute("nickname");
        if (nickname != null) {
            // Llamada al método cerrarSesion del servicio
            this.iUsuarioService.cerrarSesion(nickname);
        }
        session.invalidate(); // Invalidar la sesión
        return "redirect:/kefa/presentacion"; // Redirigir a la página de inicio de sesión
    }

    @GetMapping("/recordar_contrasenia")
    public String recordarContrasenia(Model model) {
        model.addAttribute("usuarioRegistroTO", new UsuarioRegistroTO());
        return "formulario_recordar_contrasenia";
    }

    @PostMapping("/recordar_contrasenia/validar")
    public String validarRecuperarContrasenia(@ModelAttribute("usuarioRegistroTO") UsuarioRegistroTO usuarioRegistroTO,
            Model model, HttpSession session) {
        if (usuarioRegistroTO.getCorreoElectronico().isEmpty() || usuarioRegistroTO.getPreguntaUno().isEmpty()
                || usuarioRegistroTO.getPreguntaDos().isEmpty() || usuarioRegistroTO.getPreguntaTres().isEmpty()) {
            model.addAttribute("error", "Todos los campos son obligatorios");
            return "formulario_recordar_contrasenia";
        }

        try {
            this.iUsuarioService.validarRecuperarContrasenia(usuarioRegistroTO.getCorreoElectronico(),
                    usuarioRegistroTO);
            session.setAttribute("correoElectronico", usuarioRegistroTO.getCorreoElectronico());
            return "redirect:/kefa/recordar_contrasenia/cambiar/" + usuarioRegistroTO.getCorreoElectronico();
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "formulario_recordar_contrasenia";
        }
    }

    @GetMapping("/recordar_contrasenia/cambiar/{correoElectronico}")
    public String recuperarContrasenia(Model model, HttpSession session) {
        String correoElectronico = (String) session.getAttribute("correoElectronico");
        if (correoElectronico == null) {
            return "redirect:/kefa/formulario_iniciar_sesion";
        }
        Usuario usuario = this.iUsuarioService.buscarPorEmail(correoElectronico);
        model.addAttribute("usuarioRegistroTO", new UsuarioRegistroTO());
        model.addAttribute("usuario", usuario);
        return "formulario_cambiar_contrasenia";
    }

    @PutMapping("/recordar_contrasenia/cambiar_contrasenia/{correoElectronico}")
    public String cambiarContrasenia(@ModelAttribute("usuarioRegistroTO") UsuarioRegistroTO usuarioRegistroTO,
            Model model, HttpSession session, @ModelAttribute("usuario") Usuario usuario) {
        if (usuarioRegistroTO.getConstrasenia().isEmpty() || usuarioRegistroTO.getConstraseniaRepetir().isEmpty()) {
            model.addAttribute("error", "Todos los campos son obligatorios");
            return "formulario_cambiar_contrasenia";
        }

        if (!usuarioRegistroTO.getConstrasenia().equals(usuarioRegistroTO.getConstraseniaRepetir())) {
            model.addAttribute("error", "Las contraseñas no coinciden");
            return "formulario_cambiar_contrasenia";
        }

        try {
            String correoElectronico = (String) session.getAttribute("correoElectronico");
            usuario= this.iUsuarioService.buscarPorEmail(correoElectronico);
            if (usuario != null) {
                this.iUsuarioService.recuperarContrasenia(correoElectronico, usuarioRegistroTO);
            return "redirect:/kefa/formulario_iniciar_sesion";
            } else {
                model.addAttribute("error", "No se encontró el correo electrónico");
                return "formulario_cambiar_contrasenia";
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "formulario_cambiar_contrasenia";
        }
    }
}
