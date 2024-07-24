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

/**
 * This class is a controller for handling requests related to other services in
 * the KEFA application.
 * It handles requests for adding comments and returns the corresponding view
 * templates.
 */
@Controller
@RequestMapping("/kefa")
public class ControllerOtrosServicios {
    @Autowired
    private IOtrosService otrosService;

    /**
     * Displays the form for user/client comments.
     *
     * @param model the model object to be used for rendering the view
     * @return the name of the view template to be rendered
     */
    @GetMapping("/comentarios")
    public String mostrarFormularioUsuaClie(Model model) {
        model.addAttribute("otroTO", new OtrosTO());
        return "formulario_comentario";
    }

    /**
     * Adds a comment to the specified OtrosTO object and saves it to the database.
     * 
     * @param otroTO  The OtrosTO object containing the comment to be added.
     * @param session The HttpSession object for retrieving the user's nickname.
     * @param model   The Model object for adding attributes to the view.
     * @return The name of the view to be rendered after adding the comment.
     */
    @PostMapping("/comentarios/añadir_comentario")
    public String aniadirComentario(@ModelAttribute("otroTO") OtrosTO otroTO, HttpSession session, Model model) {
        Predicate<OtrosTO> validar = otro -> otro.getComentario() != null && !otro.getComentario().isEmpty();
        if (validar.test(otroTO)) {
            String nickname = (String) session.getAttribute("nickname");
            this.otrosService.guardarComentario(otroTO, nickname);
            model.addAttribute("mensajeExito", "El comentario ha sido añadido exitosamente.");
            return "formulario_comentario";
        } else {
            model.addAttribute("error",
                    "No se realizo el comentario, por favor verifique el dato ingresado");
            return "formulario_comentario";
        }
    }

    /**
     * Displays the form for handling returns.
     *
     * @param model the model object to be used for rendering the view
     * @return the name of the view template to be rendered
     */
    @GetMapping("/devoluciones")
    public String mostrarFormularioDevolucion(Model model) {
        model.addAttribute("otroTO", new OtrosTO());
        return "formulario_devolucion";
    }

    /**
     * Adds a new devolución (return) to the system.
     * 
     * @param otroTO  The OtrosTO object containing the devolución details.
     * @param session The HttpSession object for the current session.
     * @param model   The Model object for the view.
     * @return The name of the view to render after adding the devolución.
     */
    @PostMapping("/devoluciones/añadir_devolucion")
    public String aniadirDevolucion(@ModelAttribute("otroTO") OtrosTO otroTO, HttpSession session, Model model) {
        Predicate<OtrosTO> validar = otro -> otro.getEstadoDevolucion() != null && !otro.getEstadoDevolucion().isEmpty()
                && otro.getMotivoDevolucion() != null && !otro.getMotivoDevolucion().isEmpty();
        if (validar.test(otroTO)) {
            String nickname = (String) session.getAttribute("nickname");
            this.otrosService.guardarDevolucion(otroTO, nickname);
            model.addAttribute("mensajeExito", "La devolución ha sido añadida exitosamente.");
            return "formulario_devolucion";
        } else {
            model.addAttribute("error",
                    "No se realizo la devolución, por favor verifique los datos ingresados");
            return "formulario_devolucion";
        }

    }
}
