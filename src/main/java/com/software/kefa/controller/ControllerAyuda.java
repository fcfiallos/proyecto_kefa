package com.software.kefa.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.repository.modelo.PreguntaFrecuente;
import com.software.kefa.repository.modelo.Rol;
import com.software.kefa.service.IPreguntaFrecuenteService;
import com.software.kefa.service.IRolService;

import jakarta.servlet.http.HttpSession;

/**
 * This class is a controller for managing help-related operations in the Kefa
 * application.
 * It handles requests related to suggestions and frequently asked questions.
 */
@Controller
@RequestMapping("/kefa")
public class ControllerAyuda {
    @Autowired
    private IPreguntaFrecuenteService preguntaFrecuenteService;

    @Autowired
    private IRolService rolService;

    /**
     * Retrieves the view for the list of products.
     * 
     * @param model   the model object to be populated with data
     * @param session the HttpSession object to retrieve session attributes
     * @return the name of the view to be rendered
     */
    @GetMapping("/lista_sugerencias")
    public String vistaListaProductos(Model model, HttpSession session) {
        String nickname = (String) session.getAttribute("nickname");
        Rol rol = this.rolService.buscarPorNickname(nickname);

        if (rol == null) {
            model.addAttribute("error", "El usuario no existe");
            return "formulario_inicio_sesion";
        }

        if (rol.getNombre().equals("Empleado")) {
            List<PreguntaFrecuente> preguntasFrecuentes = this.preguntaFrecuenteService.buscarTodo();
            model.addAttribute("preguntasFrecuentes", preguntasFrecuentes);
            return "vista_sugerencias_empleado";
        }

        List<PreguntaFrecuente> preguntasFrecuentes = this.preguntaFrecuenteService.buscarTodo();
        model.addAttribute("preguntasFrecuentes", preguntasFrecuentes);
        return "vista_sugerencias";
    }

    /**
     * Displays the form for adding a new frequently asked question.
     *
     * @param model the model object to be used for rendering the view
     * @return the name of the view to be rendered
     */
    @GetMapping("/formulario_añadir_nueva_ayuda")
    public String mostrarFormularioUsuaClie(Model model) {
        model.addAttribute("preguntaFrecuente", new PreguntaFrecuente());
        return "formulario_añadir_pre_frecuente";
    }

    /**
     * Adds a new comment or suggestion to the system.
     * 
     * @param preguntaFrecuente The PreguntaFrecuente object containing the comment
     *                          details.
     * @param session           The HttpSession object for maintaining session data.
     * @param model             The Model object for adding attributes to the view.
     * @return The view name to redirect to after adding the comment.
     */
    @PostMapping("/añadir_sugerencia")
    public String añadirComentario(@ModelAttribute("preguntaFrecuente") PreguntaFrecuente preguntaFrecuente,
            HttpSession session, Model model) {
        Predicate<PreguntaFrecuente> validar = prfr -> prfr.getPregunta().length() <= 250
                && !prfr.getPregunta().isEmpty()
                && prfr.getRespuesta().length() <= 250 && !prfr.getRespuesta().isEmpty()
                && !prfr.getCategoria().isEmpty();
        if (validar.test(preguntaFrecuente)) {
            String nickname = (String) session.getAttribute("nickname");
            this.preguntaFrecuenteService.guardar(preguntaFrecuente, nickname);
            return "redirect:/kefa/lista_sugerencias";
        } else {
            model.addAttribute("error",
                    "No se registro la sugerencia, por favor verifique el dato ingresado");
            return "formulario_añadir_pre_frecuente";
        }
    }

    /**
     * Retrieves the form for updating a frequently asked question based on the
     * provided code.
     *
     * @param codigo The code of the frequently asked question to be updated.
     * @param model  The model object used to pass data to the view.
     * @return The name of the view template for updating the frequently asked
     *         question.
     */
    @GetMapping("/formulario_actualizar_sugerencia/{codigo}")
    public String mostrarFormularioActualizarUsuaClie(@PathVariable("codigo") Integer codigo, Model model) {
        PreguntaFrecuente preguntaFrecuente = this.preguntaFrecuenteService.buscarPorId(codigo);
        model.addAttribute("preguntaFrecuente", preguntaFrecuente);
        return "formulario_actualizar_pre_frecuente";
    }

    /**
     * Displays the form for updating a suggestion.
     *
     * @param codigo            The code of the suggestion to be updated.
     * @param preguntaFrecuente The updated suggestion data.
     * @param model             The model object for passing data to the view.
     * @return The view name to redirect to after updating the suggestion.
     */
    @PutMapping("/actualizar_sugerencia/{codigo}")
    public String mostrarFormularioActulizar(@PathVariable("codigo") Integer codigo,
            PreguntaFrecuente preguntaFrecuente, Model model) {
        PreguntaFrecuente prFrAux = this.preguntaFrecuenteService.buscarPorId(codigo);
        if (prFrAux != null && prFrAux.getId().equals(codigo)) {
            prFrAux.setCategoria(preguntaFrecuente.getCategoria());
            prFrAux.setPregunta(preguntaFrecuente.getPregunta());
            prFrAux.setRespuesta(preguntaFrecuente.getRespuesta());
            prFrAux.setFecha_creacion(LocalDateTime.now());
            this.preguntaFrecuenteService.actualizar(prFrAux);
            return "redirect:/kefa/lista_sugerencias";
        } else {
            model.addAttribute("error", "No se actualizaron los datos de la sugerencia, intente nuevamente");
            return "formulario_actualizar_pre_frecuente";
        }
    }
}
