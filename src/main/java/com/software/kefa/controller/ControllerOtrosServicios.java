package com.software.kefa.controller;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.service.IOtrosService;
import com.software.kefa.service.modelosto.OtrosTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kefa")
public class ControllerOtrosServicios {
    @Autowired
    private IOtrosService otrosService;

    @GetMapping("/formulario_otros_servicio")
    public String mostrarFormularioUsuaClie(Model model) {
        model.addAttribute("otroTO", new OtrosTO());
        return "formulario_come_devo";
    }

    @PostMapping("/formulario_otros_servicio/añadir_comentario")
    public String añadirComentario(@ModelAttribute("otroTO") OtrosTO otroTO, HttpSession session, Model model) {
        Predicate<OtrosTO> validar = otro -> otro.getComentario() != null && !otro.getComentario().isEmpty();
        if (validar.test(otroTO)) {
            String nickname = (String) session.getAttribute("nickname");
            this.otrosService.guardarComentario(otroTO, nickname);
            model.addAttribute("mensajeExito", "El comentario ha sido añadido exitosamente.");
            return "formulario_come_devo";
        } else {
            model.addAttribute("error",
                    "No se realizo el comentario, por favor verifique el dato ingresado");
            return "formulario_come_devo";
        }
    }

    @PostMapping("/formulario_otros_servicio/añadir_devolucion")
    public String añadirDevolucion(@ModelAttribute("otroTO") OtrosTO otroTO, HttpSession session, Model model) {
        Predicate<OtrosTO> validar = otro -> otro.getEstadoDevolucion() != null && !otro.getEstadoDevolucion().isEmpty()
                && otro.getMotivoDevolucion() != null && !otro.getMotivoDevolucion().isEmpty();
        if (validar.test(otroTO)) {
            String nickname = (String) session.getAttribute("nickname");
            this.otrosService.guardarDevolucion(otroTO, nickname);
            model.addAttribute("mensajeExito", "La devolución ha sido añadida exitosamente.");
            return "formulario_come_devo";
        } else {
            model.addAttribute("error",
                    "No se realizo la devolución, por favor verifique los datos ingresados");
            return "formulario_come_devo";
        }
    }
}
