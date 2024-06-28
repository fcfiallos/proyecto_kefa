package com.software.kefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.repository.modelo.modelosdto.UsuarioPerfilDTO;
import com.software.kefa.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kefa")
public class ControllerUsuarioPerfil {
    @Autowired
    private IUsuarioService iUsuarioService;

    @GetMapping("/perfil_usuario")
    public String mostrarFormularioUsuaClie(HttpSession session, Model model) {
        // Obtener el nickname de la sesión
        String nickname = (String) session.getAttribute("nickname");

        if (nickname != null) {
            UsuarioPerfilDTO usuario = this.iUsuarioService.buscarInformacion(nickname);
            model.addAttribute("usuario", usuario);
            return "vista_perfil_usuario";
        } else {
            // Manejar el caso en que no hay una sesión o nickname disponible
            return "redirect:/kefa/formulario_iniciar_sesion";
        }
    }

    @GetMapping("/formulario_actualizar_perfil")
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

    @PutMapping("/actualizar_perfil")
    public String mostrarFormularioActualizar(HttpSession session, Usuario usuario, Model model) {
        // Obtener el nickname de la sesión
        String nickname = (String) session.getAttribute("nickname");

        if (nickname != null) {
            Usuario usuaAux = this.iUsuarioService.buscarPorNickname(nickname);
            if (usuaAux != null) {
                usuaAux.setApellido(usuario.getApellido());
                usuaAux.setNombre(usuario.getNombre());
                usuaAux.setCorreoElectronico(usuario.getCorreoElectronico());
                usuaAux.setTelefono(usuario.getTelefono());
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
