package com.software.kefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.software.kefa.repository.modelo.ListaDeseos;
import com.software.kefa.service.IListaDeseoService;

import jakarta.servlet.http.HttpSession;

/**
 * This class is a controller for managing the wishlist functionality in the application.
 * It handles requests related to viewing, adding, and removing products from the wishlist.
 */
@Controller
@RequestMapping("/kefa")
public class ControllerListaDeseo {

    @Autowired
    private IListaDeseoService iListaDeseoService;

    @GetMapping("/lista_deseos")
    public String verListaDeseos(Model model, HttpSession session) {
        ListaDeseos listaDeseo = (ListaDeseos) session.getAttribute("miListaDeseo");
        if (listaDeseo == null) {
            listaDeseo = new ListaDeseos();
        }
        session.setAttribute("miListaDeseo", listaDeseo);
        model.addAttribute("listaDeseos", listaDeseo);
        return "vista_lista_ListaDeseo";
    }

    @PostMapping("/lista_deseos/agregar")
    public String agregarProductoALaListaDeseos(@RequestParam("productoId") Integer productoId, HttpSession session,
            Model model) {
        ListaDeseos miListaDeseo = new ListaDeseos();
        try {
            String nickname = (String) session.getAttribute("nickname");
            miListaDeseo = this.iListaDeseoService.agregarProductoALaLista(productoId, nickname, miListaDeseo);
            session.setAttribute("miListaDeseo", miListaDeseo);
        } catch (Exception e) {
            model.addAttribute("error", "Error al agregar producto a la lista de deseos: " + e.getMessage());
            return "redirect:/kefa/lista_categoria_productos";
        }
        return "redirect:/kefa/lista_categoria_productos";
    }

    @PostMapping("/lista_deseos/eliminar/{id}")
    public String eliminarProductoDelCarrito(@PathVariable Integer id, HttpSession session,
            Model model) {
        ListaDeseos listaDeseo = (ListaDeseos) session.getAttribute("miListaDeseo");
        if (listaDeseo.getId() != null) {
            try {
                this.iListaDeseoService.actualizar(listaDeseo);
            } catch (Exception e) {
                model.addAttribute("error", "Error al eliminar producto del carrito: " + e.getMessage());
            }
        }
        return "redirect:/kefa/lista_deseos";
    }

}
