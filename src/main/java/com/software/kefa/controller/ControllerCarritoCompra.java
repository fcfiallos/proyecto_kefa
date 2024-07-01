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
            List<Producto> carritoCompra = iCarritoCompraService.buscarTodo(carritoId);
            model.addAttribute("carritoCompra", carritoCompra);
        } else {
            model.addAttribute("carritoCompra", Collections.emptyList());
        }
        return "vista_lista_CarritoCompra";
    }

    @GetMapping("/productos")
    public String vistaListaProductosDisponibles(Model model, HttpSession session) {
        Integer carritoId = (Integer) session.getAttribute("carritoId");
        List<Producto> productos = iCarritoCompraService.buscarTodo(carritoId);
        model.addAttribute("productos", productos);
        return "vista_lista_producto";
    }

    @PostMapping("/carrito/agregar")
    public String agregarProductoAlCarrito(@RequestParam("productoId") Integer productoId, HttpSession session,
            Model model, @RequestParam("cantidad") Integer cantidad){
        Integer carritoId = (Integer) session.getAttribute("carritoId");
        if (carritoId == null) {
            carritoId = crearNuevoCarrito(productoId,session,cantidad); // Método para crear un nuevo carrito y guardar su ID en la sesión
        }

        try {
            String nickname = (String) session.getAttribute("nickname");
            iCarritoCompraService.agregarProductoAlCarrito(carritoId, productoId, nickname, cantidad);
            model.addAttribute("mensaje", "Cantidad actualizada exitosamente.");
            model.addAttribute("mensaje", "Producto agregado al carrito de compras exitosamente.");
        } catch (Exception e) {
            model.addAttribute("error", "Error al agregar producto al carrito: " + e.getMessage());
        } 
        return "redirect:/kefa/productos";
    }

    @PostMapping("/carrito/eliminar")
    public String eliminarProductoDelCarrito(@RequestParam("productoId") Integer productoId, HttpSession session,
            Model model) {
        Integer carritoId = (Integer) session.getAttribute("carritoId");
        if (carritoId != null) {
            try {
                iCarritoCompraService.eliminarProductoDelCarrito(carritoId, productoId);
                model.addAttribute("mensaje", "Producto eliminado del carrito de compras exitosamente.");
            } catch (Exception e) {
                model.addAttribute("error", "Error al eliminar producto del carrito: " + e.getMessage());
            }
        }
        return "redirect:/kefa/carrito";
    }

    private Integer crearNuevoCarrito(@RequestParam("productoId") Integer productoId,HttpSession session, Integer cantidad) {
        // Crear un nuevo carrito de compras y guardar su ID en la sesión
        CarritoCompra nuevoCarrito = new CarritoCompra();
        String nickname = (String) session.getAttribute("nickname");
        iCarritoCompraService.guardar(nuevoCarrito, nickname, productoId,cantidad);
        session.setAttribute("carritoId", nuevoCarrito.getId());
        return nuevoCarrito.getId();
    }
}
