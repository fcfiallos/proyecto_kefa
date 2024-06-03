package com.software.kefa.controller;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.excepcion.UsuarioExisteExcepcion;
import com.software.kefa.service.IUsuarioService;
import com.software.kefa.service.modelosto.UsuarioRegistroTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping ("/kefa")
public class ControllerUsuarioLogin {
    @Autowired
    private IUsuarioService iUsuarioService;

    @GetMapping ("/formulario_registro")  
    public String mostrarFormularioUsuaClie(Model model) {
		model.addAttribute("usuarioRegistroTO", new UsuarioRegistroTO());
		return "formulario_registro_ClieUsua";
	}

    @PostMapping("/registrar") 
    public String registrarUsuaClie(@ModelAttribute("usuarioRegistroTO") UsuarioRegistroTO usuarioRegistroTO, Model model){
        Predicate <UsuarioRegistroTO> validar = usuaClie -> !usuaClie.getApellido().isEmpty() && !usuaClie.getCedula().isEmpty() && !usuaClie.getConstrasenia().isEmpty() && !usuaClie.getConstraseniaRepetir().isEmpty() && !usuaClie.getCorreoElectronico().isEmpty() && !usuaClie.getGenero().isEmpty() && !usuaClie.getNickname().isEmpty() && !usuaClie.getNombre().isEmpty() && !usuaClie.getPreguntaDos().isEmpty() && !usuaClie.getPreguntaTres().isEmpty() && !usuaClie.getPreguntaUno().isEmpty() && !usuaClie.getTelefono().isEmpty() && !usuaClie.getRol().isEmpty();
        
        if (validar.test(usuarioRegistroTO)) {
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
        } else {
            model.addAttribute("error", "Todos los campos son obligatorios");
            return "formulario_registro_ClieUsua";
        }
    }

    @GetMapping ("/formulario_iniciar_sesion")  
    public String mostrarFormularioIniciarSecion(Model model) {
		model.addAttribute("usuarioRegistroTO", new UsuarioRegistroTO());
		return "formulario_inicio_sesion";
	}

}
