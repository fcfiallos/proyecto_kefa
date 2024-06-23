package com.software.kefa.controller;

import java.math.BigDecimal;
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
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.service.IPreguntaFrecuenteService;
import com.software.kefa.service.modelosto.OtrosTO;
import com.software.kefa.service.modelosto.ProductoTO;

@Controller
@RequestMapping("/kefa")
public class ControllerAyuda {
    @Autowired
    private IPreguntaFrecuenteService preguntaFrecuenteService;

    @GetMapping("/lista_sugerencias")
    public String vistaListaProductos(Model model) {
        List<PreguntaFrecuente> preguntasFrecuentes = this.preguntaFrecuenteService.buscarTodo();
        model.addAttribute("preguntasFrecuentes", preguntasFrecuentes);
        return "vista_sugerencias";
    }
    
    @GetMapping("/formulario_añadir_nueva_ayuda")
    public String mostrarFormularioUsuaClie(Model model) {
        model.addAttribute("preguntaFrecuente", new PreguntaFrecuente());
        return "formulario_añadir_pre_frecuente";
    }

    @PostMapping("/añadir_sugerencia")
    public String añadirComentario(@ModelAttribute("preguntaFrecuente") PreguntaFrecuente preguntaFrecuente, Model model) {
        Predicate<PreguntaFrecuente> validar = prfr -> prfr.getPregunta().length() <= 250 && !prfr.getPregunta().isEmpty()
                && prfr.getRespuesta().length() <= 250 && !prfr.getRespuesta().isEmpty() && !prfr.getCategoria().isEmpty();
        if (validar.test(preguntaFrecuente)) {
            this.preguntaFrecuenteService.guardar(preguntaFrecuente);
            return "redirect:/kefa/lista_sugerencias";
        } else {
            model.addAttribute("error",
                    "No se registro la sugerencia, por favor verifique el dato ingresado");
            return "formulario_añadir_pre_frecuente";
        }
    }

    @GetMapping("/formulario_actualizar_sugerencia/{codigo}")
    public String mostrarFormularioActualizarUsuaClie(@PathVariable("codigo") Integer codigo, Model model) {
        PreguntaFrecuente preguntaFrecuente = this.preguntaFrecuenteService.buscarPorId(codigo);
        model.addAttribute("preguntaFrecuente", preguntaFrecuente);
        return "formulario_actualizar_pre_frecuente";
    }

    @PutMapping("/actualizar_sugerencia/{codigo}")
    public String mostrarFormularioActulizar(@PathVariable("codigo") Integer codigo, PreguntaFrecuente preguntaFrecuente, Model model) {
        PreguntaFrecuente prFrAux = this.preguntaFrecuenteService.buscarPorId(preguntaFrecuente.getId());
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
