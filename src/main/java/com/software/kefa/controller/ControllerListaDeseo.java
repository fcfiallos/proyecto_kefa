package com.software.kefa.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.software.kefa.repository.modelo.ListaDeseos;
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.service.IListaDeseoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kefa")
public class ControllerListaDeseo {

    @Autowired
    private IListaDeseoService iListaDeseoService;

    // Métodos para la lista de deseos
    @GetMapping("/lista_deseos")
    public String vistaListaDeseos(Model model, HttpSession session) {
        Integer listaDeseosId = (Integer) session.getAttribute("listaDeseosId");
        if (listaDeseosId != null) {
            List<Producto> listaDeseos = iListaDeseoService.buscarTodo(listaDeseosId);
            model.addAttribute("listaDeseos", listaDeseos);
        } else {
            model.addAttribute("listaDeseos", Collections.emptyList());
        }
        return "vista_lista_ListaDeseo";
    }

    @PostMapping("/lista_deseos/agregar")
    public String agregarProductoALaListaDeseos(@RequestParam("productoId") Integer productoId, HttpSession session, Model model) {
        Integer listaDeseosId = (Integer) session.getAttribute("listaDeseosId");
        if (listaDeseosId == null) {
            listaDeseosId = crearNuevaListaDeseos(productoId,session);
        }

        try {
            this.iListaDeseoService.agregarProductoALaLista(listaDeseosId, productoId);
            model.addAttribute("mensaje", "Producto agregado a la lista de deseos exitosamente.");
        } catch (Exception e) {
            model.addAttribute("error", "Error al agregar producto a la lista de deseos: " + e.getMessage());
        }
        return "redirect:/kefa/productos";
    }

    private Integer crearNuevaListaDeseos(@RequestParam("productoId") Integer productoId,HttpSession session) {
        String nickname = (String) session.getAttribute("nickname");
        ListaDeseos nuevaListaDeseos = new ListaDeseos();
        iListaDeseoService.guardar(nuevaListaDeseos,nickname,productoId);
        session.setAttribute("listaDeseosId", nuevaListaDeseos.getId());
        return nuevaListaDeseos.getId();
    }
}
