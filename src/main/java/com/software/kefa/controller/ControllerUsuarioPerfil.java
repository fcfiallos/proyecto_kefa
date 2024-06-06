package com.software.kefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.excepcion.UsuarioExisteExcepcion;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.repository.modelo.modelosdto.UsuarioPerfilDTO;
import com.software.kefa.service.IUsuarioService;
import com.software.kefa.service.modelosto.UsuarioRegistroTO;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/kefa")
public class ControllerUsuarioPerfil {
    @Autowired
    private IUsuarioService iUsuarioService;

    @GetMapping("/formulario_perfil/{nickname}")
    public String mostrarFormularioUsuaClie(@PathVariable("nickname") String nickname, Model model) {
        UsuarioPerfilDTO usuarioPerfilDTO = this.iUsuarioService.buscarInformacion(nickname);
        model.addAttribute("usuarioPerfilDTO", usuarioPerfilDTO);
        return "vista_perfil_usuario";
    }

    @PutMapping("/actualizar_perfil/{nickname}")
    public String mostrarFormularioActulizar(@PathVariable("nickname") String nickname, UsuarioRegistroTO registroTO, Model model) {

        Usuario usuaAux = this.iUsuarioService.buscarPorNickname(nickname);

        if (usuaAux.getNickname().equals(nickname)) {
            usuaAux.setApellido(registroTO.getApellido());
            usuaAux.setNombre(registroTO.getNombre());
            usuaAux.setCorreoElectronico(registroTO.getCorreoElectronico());
            usuaAux.setGenero(registroTO.getGenero());
            usuaAux.setTelefono(registroTO.getTelefono());
            this.iUsuarioService.actualizar(usuaAux);
            return "formulario_actualizar_datos_Clie";
        } else {
            model.addAttribute("error", "No se ejecutó la actualización de los datos del cliente.");
            return "formulario_actualizar_datos_Clie";
        }

    }

}
