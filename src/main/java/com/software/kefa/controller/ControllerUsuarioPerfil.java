package com.software.kefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String mostrarFormularioUsuaClie(@PathVariable("nickname") String nickname,Model model) {
        UsuarioPerfilDTO usuarioPerfilDTO = this.iUsuarioService.buscarInformacion(nickname);
        model.addAttribute("usuarioPerfilDTO", usuarioPerfilDTO);
        return "vista_perfil_usuario";
    }



    @PutMapping("/actualizar_perfil/{nickname}")
    public String mostrarFormularioActulizar(@PathVariable("nickname") String nickname, UsuarioRegistroTO usuarioRegistroTO, Model model) {
        Usuario usuaAux = this.iUsuarioService.buscarPorNickname(nickname);
        if (usuaAux != null && usuaAux.getNickname().equals(nickname)) {
            usuaAux.setApellido(usuarioRegistroTO.getApellido());
            usuaAux.setNombre(usuarioRegistroTO.getNombre());
            usuaAux.setCorreoElectronico(usuarioRegistroTO.getCorreoElectronico());
            usuaAux.setGenero(usuarioRegistroTO.getGenero());
            usuaAux.setTelefono(usuarioRegistroTO.getTelefono());
            this.iUsuarioService.actualizar(usuaAux);
            return "formulario_actualizar_datos_Clie";
        } else {
            model.addAttribute("error", "No se actualizaron los datos del usuario");
            return "formulario_actualizar_datos_Clie";
        }
    }

}
