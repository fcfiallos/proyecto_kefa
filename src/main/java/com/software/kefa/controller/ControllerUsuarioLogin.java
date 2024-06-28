package com.software.kefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.excepcion.MensajeExisteExcepcion;
import com.software.kefa.service.IUsuarioService;
import com.software.kefa.service.modelosto.UsuarioRegistroTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kefa")
public class ControllerUsuarioLogin {
    @Autowired
    private IUsuarioService iUsuarioService;

    @GetMapping("/presentacion")
    public String mostrarPaginaDeLogin() {
        // Retorna el nombre del archivo HTML sin la extensión
        return "formulario_presentacion"; // Asume que tienes un archivo login.html en tu carpeta de recursos
    }

    @GetMapping("/formulario_registro")
    public String mostrarFormularioUsuaClie(Model model) {
        model.addAttribute("usuarioRegistroTO", new UsuarioRegistroTO());
        return "formulario_registro_ClieUsua";
    }

    @PostMapping("/registrar")
    public String registrarUsuaClie(@ModelAttribute("usuarioRegistroTO") UsuarioRegistroTO usuarioRegistroTO,
            Model model) {
        if (usuarioRegistroTO.getCedula().isEmpty() || usuarioRegistroTO.getCedula().length() > 10) {
            model.addAttribute("error", "La cédula no debe exceder los 10 caracteres");
            return "formulario_registro_ClieUsua";
        }

        // Validar otros campos
        if (usuarioRegistroTO.getApellido().isEmpty() ||
                usuarioRegistroTO.getConstrasenia().isEmpty() ||
                usuarioRegistroTO.getConstraseniaRepetir().isEmpty() ||
                usuarioRegistroTO.getCorreoElectronico().isEmpty() ||
                usuarioRegistroTO.getGenero().isEmpty() ||
                usuarioRegistroTO.getNickname().isEmpty() ||
                usuarioRegistroTO.getNombre().isEmpty() ||
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

    @GetMapping("/formulario_iniciar_sesion")
    public String mostrarFormularioIniciarSecion(Model model) {
        model.addAttribute("usuarioRegistroTO", new UsuarioRegistroTO());
        return "formulario_inicio_sesion";
    }

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

}
