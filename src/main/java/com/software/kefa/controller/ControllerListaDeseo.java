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
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.service.IListaDeseoService;
import com.software.kefa.service.IProductoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kefa")
public class ControllerListaDeseo {

    @Autowired
    private IListaDeseoService iListaDeseoService;

    @Autowired
    private IProductoService productoService;

    @GetMapping("/listaDeseos/ver/{id}")
    public String verListaDeseos(@PathVariable("id") Integer id, Model model) {
        ListaDeseos listaDeseos = iListaDeseoService.obtenerListaDeseosPorId(id);
        if (listaDeseos == null) {
            model.addAttribute("error", "La lista de deseos no existe.");
        }
        model.addAttribute("listaDeseos", listaDeseos);
        return "vista_lista_deseos";
    }

    @PostMapping("/listaDeseos/agregarProducto")
    public String agregarProducto(@RequestParam Integer listaDeseosId, @RequestParam Integer productoId, Model model, HttpSession session) {
        Producto producto = productoService.buscarPorId(productoId);
        try {
            String nickname = (String) session.getAttribute("nickname");
            iListaDeseoService.agregarProducto(listaDeseosId, producto, nickname);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/listaDeseos/ver/" + listaDeseosId;
    }

    @PostMapping("/listaDeseos/eliminarProducto")
    public String eliminarProducto(@RequestParam Integer listaDeseosId, @RequestParam Integer productoId, Model model) {
        Producto producto = productoService.buscarPorId(productoId);
        try {
            iListaDeseoService.eliminarProducto(listaDeseosId, producto);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/listaDeseos/ver/" + listaDeseosId;
    }

    
    
}
