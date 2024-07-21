package com.software.kefa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.repository.modelo.Notificacion;
import com.software.kefa.service.INotificacionService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/kefa")
public class ControllerNotificaciones {
    @Autowired
    private INotificacionService notificacionService;
    
    @GetMapping("/notificaciones")
    String mostrarListaNotificaciones(Model model, HttpSession session) {
        String nickname = (String) session.getAttribute("nickname");
        List<Notificacion> notificaciones = notificacionService.buscarTodoPorNickname(nickname);
        model.addAttribute("notificaciones", notificaciones);
        return "vista_lista_notificaciones";
    }
}
