package com.software.kefa.controller;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.excepcion.MensajeExisteExcepcion;
import com.software.kefa.repository.modelo.Promocion;
import com.software.kefa.service.IPromocionService;
import com.software.kefa.service.modelosto.PromocionTO;

@Controller
@RequestMapping("/kefa")
public class ControllerPromocion {

    @Autowired
    private IPromocionService promocionService;

    @GetMapping("/promocion")
    String mostrarListaPromociones(Model model) {
        List<Promocion> promociones = promocionService.buscarTodo();
        model.addAttribute("promociones", promociones);
        return "vista_lista_promocion";
    }

    @GetMapping("/promocion/formulario_nueva_promocion")
    String mostrarFormularioNuevaPromocion(Model model) {
        model.addAttribute("promocion", new PromocionTO());
        return "formulario_promocion";
    }

    @PostMapping("/promocion/formulario_nueva_promocion/crear")
    String crearPromocion(@ModelAttribute("promocion") PromocionTO promocionTO, Model model) {
        Predicate<PromocionTO> promocionValida = promocion -> !promocion.getDescuento().isEmpty()
                && !promocion.getTipo().isEmpty();
        if (promocionValida.test(promocionTO)) {
            try {
                promocionService.guardar(promocionTO);
                return "redirect:/kefa/promocion";
            } catch (MensajeExisteExcepcion e) {
                model.addAttribute("error", e.getMessage());
                return "formulario_producto";
            }
        } else {
            model.addAttribute("error",
                    "Validación de la promocion fallo.");
            return "formulario_promocion";
        }

    }
}
