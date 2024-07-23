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
import com.software.kefa.repository.modelo.Rol;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.service.IPromocionService;
import com.software.kefa.service.IRolService;
import com.software.kefa.service.IUsuarioService;
import com.software.kefa.service.modelosto.PromocionTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kefa")
public class ControllerPromocion {

    @Autowired
    private IPromocionService promocionService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

    @GetMapping("/promocion")
    String mostrarListaPromociones(Model model, HttpSession session) {
        List<Promocion> promociones = promocionService.buscarTodo();

        String nickname = (String) session.getAttribute("nickname");
        Rol rol = this.rolService.buscarPorNickname(nickname);

        if (rol == null) {
            model.addAttribute("error", "El usuario no existe");
            return "formulario_inicio_sesion";
        }

        if (rol.getNombre().equals("Empleado")) {
            model.addAttribute("promociones", promociones);
            return "vista_lista_promocion_empleado";
        }
        model.addAttribute("promociones", promociones);
        return "vista_lista_promocion";
    }

    @GetMapping("/promocion/formulario_nueva_promocion")
    String mostrarFormularioNuevaPromocion(Model model) {
        model.addAttribute("promocion", new PromocionTO());
        return "formulario_promocion";
    }

    @PostMapping("/promocion/formulario_nueva_promocion/crear")
    String crearPromocion(@ModelAttribute("promocion") PromocionTO promocionTO, Model model, HttpSession session) {
        Predicate<PromocionTO> promocionValida = promocion -> !promocion.getDescuento().isEmpty()
                && !promocion.getTipo().isEmpty();
        if (promocionValida.test(promocionTO)) {

            try {
                String nickname = (String) session.getAttribute("nickname");

                Usuario usuario = this.usuarioService.buscarPorNickname(nickname);

                if (usuario == null) {
                    return "redirect:/kefa/formulario_iniciar_sesion";

                }

                promocionService.guardar(promocionTO, nickname);
                return "redirect:/kefa/promocion";
            } catch (MensajeExisteExcepcion e) {
                model.addAttribute("error", e.getMessage());
                return "formulario_producto";
            }
        } else {
            model.addAttribute("error",
                    "Validación de la promoción fallo.");
            return "formulario_promocion";
        }

    }
}
