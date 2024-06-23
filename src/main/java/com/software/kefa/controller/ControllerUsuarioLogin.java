package com.software.kefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.excepcion.UsuarioExisteExcepcion;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.service.IUsuarioService;
import com.software.kefa.service.modelosto.UsuarioRegistroTO;

@Controller
@RequestMapping("/kefa")
public class ControllerUsuarioLogin {
    @Autowired
    private IUsuarioService iUsuarioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
                usuarioRegistroTO.getTelefono().isEmpty() ){

            model.addAttribute("error", "Todos los campos son obligatorios");
            return "formulario_registro_ClieUsua";
        }

        try {
            this.iUsuarioService.guardar(usuarioRegistroTO);
            return "redirect:/kefa/formulario_iniciar_sesion";
        } catch (UsuarioExisteExcepcion e) {
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
    public String iniciarSesion(@ModelAttribute("usuarioRegistroTO") UsuarioRegistroTO usuarioRegistroTO, Model model) {
        Usuario usuario = this.iUsuarioService.buscarPorNickname(usuarioRegistroTO.getNickname());
        
        if (usuario != null && passwordEncoder.matches(usuarioRegistroTO.getConstrasenia(), usuario.getConstrasenia())) {
            return "redirect:/kefa/lista_categoria_productos";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "formulario_iniciar_sesion";
        }
    }

}
