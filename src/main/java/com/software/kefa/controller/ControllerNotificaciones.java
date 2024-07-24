package com.software.kefa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.repository.modelo.Notificacion;
import com.software.kefa.repository.modelo.Rol;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.service.INotificacionService;
import com.software.kefa.service.IRolService;
import com.software.kefa.service.UsuarioServiceImple;

import jakarta.servlet.http.HttpSession;

/**
 * This class is a controller for handling notifications related requests in the
 * Kefa application.
 */
@Controller
@RequestMapping("/kefa")
public class ControllerNotificaciones {
    @Autowired
    private INotificacionService notificacionService;

    @Autowired
    private IRolService rolService;

    @Autowired
    private UsuarioServiceImple usuarioService;

    /**
     * Retrieves and displays a list of notifications for a user.
     * 
     * @param model   the model object to add attributes to
     * @param session the HttpSession object to retrieve the user's nickname from
     * @return the view name to render based on the user's role
     */
    @GetMapping("/notificaciones")
    String mostrarListaNotificaciones(Model model, HttpSession session) {
        String nickname = (String) session.getAttribute("nickname");
        List<Notificacion> notificaciones = notificacionService.buscarTodoPorNickname(nickname);

        Rol rol = this.rolService.buscarPorNickname(nickname);

        Usuario usuario = this.usuarioService.buscarPorNickname(nickname);

        if (usuario == null) {
            model.addAttribute("error", "El usuario no existe");
            return "redirect:/kefa/formulario_iniciar_sesion";

        }

        if (rol == null) {
            model.addAttribute("error", "El usuario no existe");
            return "formulario_inicio_sesion";
        }

        if (rol.getNombre().equals("Empleado")) {
            model.addAttribute("notificaciones", notificaciones);
            return "vista_lista_notificaciones_empleado";
        }

        model.addAttribute("notificaciones", notificaciones);
        return "vista_lista_notificaciones";
    }
}
