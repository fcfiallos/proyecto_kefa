package com.software.kefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.repository.modelo.Ubicacion;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.repository.modelo.modelosdto.UsuarioPerfilDTO;
import com.software.kefa.service.IUsuarioService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/kefa")
public class ControllerUsuarioPerfil {
    @Autowired
    private IUsuarioService iUsuarioService;

    @GetMapping("/perfil_usuario/{nickname}")
    public String mostrarFormularioUsuaClie(@PathVariable("nickname") String nickname,Model model) {
        UsuarioPerfilDTO usuario = this.iUsuarioService.buscarInformacion(nickname);
        model.addAttribute("usuario", usuario);
        return "vista_perfil_usuario";
    }

    @GetMapping("/formulario_actualizar_perfil/{nickname}")
    public String mostrarFormularioActualizarUsuaClie(@PathVariable("nickname") String nickname, Model model) {
        Usuario usuario= this.iUsuarioService.buscarPorNickname(nickname);
        model.addAttribute("usuario", usuario);
        return "formulario_actualizar_datos_Clie";
    }


    @PutMapping("/actualizar_perfil/{nickname}")
    public String mostrarFormularioActulizar(@PathVariable("nickname") String nickname, Usuario usuario, Model model) {
        Usuario usuaAux = this.iUsuarioService.buscarPorNickname(nickname);
        if (usuaAux != null && usuaAux.getNickname().equals(nickname)) {
            Ubicacion ubicacion = new Ubicacion();
            usuaAux.setApellido(usuario.getApellido());
            usuaAux.setNombre(usuario.getNombre());
            usuaAux.setCorreoElectronico(usuario.getCorreoElectronico());
            usuaAux.setTelefono(usuario.getTelefono());
            this.iUsuarioService.actualizar(usuaAux);
            return "redirect:/kefa/lista_categoria_productos";
        } else {
            model.addAttribute("error", "No se actualizaron los datos del usuario");
            return "formulario_actualizar_datos_Clie";
        }
    }

}
