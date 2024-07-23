package com.software.kefa.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.service.ICarritoCompraService;
import com.software.kefa.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kefa")
public class ControllerCarritoCompra {
    @Autowired
    private ICarritoCompraService iCarritoCompraService;

     @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/carrito")
    public String vistaListaCarrito(Model model, HttpSession session) {
        CarritoCompra carrito = (CarritoCompra) session.getAttribute("miCarrito");

        try {
            if (carrito == null) {
                carrito = new CarritoCompra(); // O maneja esta situación de manera adecuada
            }
            if (carrito.getDetalleOrden() == null) {
                carrito.setDetalleOrden(Collections.emptyList()); // O maneja esta situación de manera adecuada
            }
        } catch (Exception e) {
            return "vista_lista_CarritoCompra";
        }

        session.setAttribute("miCarrito", carrito);

        model.addAttribute("carrito", carrito);
        return "vista_lista_CarritoCompra";
    }

    @PostMapping("/carrito/agregar")
    public String agregarProductoAlCarrito(@RequestParam("productoId") Integer productoId, HttpSession session,
            Model model, @RequestParam("cantidad") Integer cantidad) {
        CarritoCompra miCarrito = new CarritoCompra();

        try {
            String nickname = (String) session.getAttribute("nickname");

            Usuario usuario = this.usuarioService.buscarPorNickname(nickname);
            if (usuario == null) {
                return "redirect:/kefa/formulario_iniciar_sesion";
            }

            miCarrito = iCarritoCompraService.agregarProductoAlCarrito(productoId, nickname, cantidad, miCarrito);
            session.setAttribute("miCarrito", miCarrito);
            return "redirect:/kefa/lista_categoria_productos";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            e.printStackTrace();
            return "vista_lista_CarritoCompra";
        }
    }

    @PostMapping("/carrito/eliminar/{id}")
    public String eliminarProductoDelCarrito(@PathVariable Integer id, HttpSession session,
            Model model) {
        CarritoCompra carritoCompra = (CarritoCompra) session.getAttribute("miCarrito");

        try {
            if (carritoCompra != null) {
                carritoCompra = iCarritoCompraService.actualizar(carritoCompra, id);
                session.setAttribute("miCarrito", carritoCompra);
            }
            return "redirect:/kefa/carrito";
        } catch (Exception e) {
            model.addAttribute("error", "Error al eliminar producto del carrito: " + e.getMessage());
            e.printStackTrace();
            return "vista_lista_CarritoCompra";
        }

    }

}
