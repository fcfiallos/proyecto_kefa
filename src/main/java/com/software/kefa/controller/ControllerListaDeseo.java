package com.software.kefa.controller;

import java.util.Collections;
import java.util.Set;

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
            Set<Producto> listaDeseos = iListaDeseoService.buscarTodo();
            model.addAttribute("listaDeseos", listaDeseos);
        } else {
            model.addAttribute("listaDeseos", Collections.emptyList());
        }
        return "vista_lista_ListaDeseo";
    }

    @PostMapping("/lista_deseos/agregar")
    public String agregarProductoAListaDeseos(@RequestParam("productoId") Integer productoId, HttpSession session, Model model) {
        Integer listaDeseosId = (Integer) session.getAttribute("listaDeseosId");
        if (listaDeseosId == null) {
            listaDeseosId = crearNuevaListaDeseos(session);  // Método para crear una nueva lista de deseos y guardar su ID en la sesión
        }

        try {
            this.iListaDeseoService.agregarProductoALaLista(listaDeseosId, productoId);
            model.addAttribute("mensaje", "Producto agregado a la lista de deseos exitosamente.");
        } catch (Exception e) {
            model.addAttribute("error", "Error al agregar producto a la lista de deseos: " + e.getMessage());
        }
        return "redirect:/kefa/lista_categoria_productos";
    }

    private Integer crearNuevaListaDeseos(HttpSession session) {
        ListaDeseos nuevaListaDeseos = new ListaDeseos();
        iListaDeseoService.guardar(nuevaListaDeseos);
        session.setAttribute("listaDeseosId", nuevaListaDeseos.getId());
        return nuevaListaDeseos.getId();
    }
}
