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

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.service.ICarritoCompraService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kefa")
public class ControllerCarritoCompra {
    @Autowired
    private ICarritoCompraService iCarritoCompraService;

    @GetMapping("/carrito")
    public String vistaListaCarrito(Model model, HttpSession session) {
        Integer carritoId = (Integer) session.getAttribute("carritoId");
        if (carritoId != null) {
            Set<Producto> carritoCompra = iCarritoCompraService.buscarTodo();
            model.addAttribute("carritoCompra", carritoCompra);
        } else {
            model.addAttribute("carritoCompra", Collections.emptyList());
        }
        return "vista_lista_CarritoCompra";
    }

    @GetMapping("/productos")
    public String vistaListaProductosDisponibles(Model model) {
        Set<Producto> productos = iCarritoCompraService.buscarTodo();
        model.addAttribute("productos", productos);
        return "vista_productos_disponibles";
    }

    @PostMapping("/carrito/agregar")
    public String agregarProductoAlCarrito(@RequestParam("productoId") Integer productoId, HttpSession session, Model model) {
        Integer carritoId = (Integer) session.getAttribute("carritoId");
        if (carritoId == null) {
            carritoId = crearNuevoCarrito(session);  // Método para crear un nuevo carrito y guardar su ID en la sesión
        }

        try {
            iCarritoCompraService.agregarProductoAlCarrito(carritoId, productoId);
            model.addAttribute("mensaje", "Producto agregado al carrito de compras exitosamente.");
        } catch (Exception e) {
            model.addAttribute("error", "Error al agregar producto al carrito: " + e.getMessage());
        }
        return "redirect:/kefa/lista_categoria_productos";
    }

    private Integer crearNuevoCarrito(HttpSession session) {
        // Crear un nuevo carrito de compras y guardar su ID en la sesión
        CarritoCompra nuevoCarrito = new CarritoCompra();
        iCarritoCompraService.guardar(nuevoCarrito);
        session.setAttribute("carritoId", nuevoCarrito.getId());
        return nuevoCarrito.getId();
    }
}
