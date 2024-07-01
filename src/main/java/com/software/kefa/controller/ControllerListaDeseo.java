package com.software.kefa.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.software.kefa.repository.modelo.ListaDeseos;
import com.software.kefa.service.IListaDeseoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kefa")
public class ControllerListaDeseo {

    @Autowired
    private IListaDeseoService iListaDeseoService;

    @Autowired
    private HttpSession session;

    private ListaDeseos obtenerListaDeseos() {
        ListaDeseos listaDeseoId = (ListaDeseos) session.getAttribute("listaDeseoId");

        if (listaDeseoId == null) {
            listaDeseoId = new ListaDeseos();
            session.setAttribute("listaDeseoId", listaDeseoId);
        }
        return listaDeseoId;
    }

    // Métodos para la lista de deseos
    @GetMapping("/lista_deseos")
    public String verListaDeseos(Model model, HttpSession session) {
        ListaDeseos listaDeseo = obtenerListaDeseos();
        if (listaDeseo == null) {
            listaDeseo = new ListaDeseos(); 
        }

        if (listaDeseo.getDetallesOrdenes() == null) {
            listaDeseo.setDetallesOrdenes(Collections.emptyList());     
        }
        model.addAttribute("listaDeseo", listaDeseo);
        return "vista_lista_ListaDeseo";
    }

    @PostMapping("/lista_deseos/agregar")
    public String agregarProductoALaListaDeseos(@RequestParam("productoId") Integer productoId, HttpSession session,
            Model model) {
        try {
            String nickname = (String) session.getAttribute("nickname");
            this.iListaDeseoService.agregarProductoALaLista(productoId, nickname);
        } catch (Exception e) {
            model.addAttribute("error", "Error al agregar producto a la lista de deseos: " + e.getMessage());
        }
        return "redirect:/kefa/lista_deseos";
    }

    @PostMapping("/lista_deseos/eliminar")
    public String eliminarProductoDelCarrito(@RequestParam("detalleOrdenId") int detalleOrdenId, HttpSession session,
            Model model) {
        Integer listaDeseoId = (Integer) session.getAttribute("listaDeseoId");
        String nickname = (String) session.getAttribute("nickname");
        if (listaDeseoId != null) {
            try {
                iListaDeseoService.eliminarProductoDeLista(detalleOrdenId, nickname);
                model.addAttribute("mensaje", "Producto eliminado de la lista de deseos exitosamente.");
            } catch (Exception e) {
                model.addAttribute("error", "Error al eliminar producto del carrito: " + e.getMessage());
            }
        }
        return "redirect:/kefa/lista_deseos";
    }

}
