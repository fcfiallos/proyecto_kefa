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
import com.software.kefa.service.ICarritoCompraService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kefa")
public class ControllerCarritoCompra {
    @Autowired
    private ICarritoCompraService iCarritoCompraService;

    @GetMapping("/carrito")
    public String vistaListaCarrito(Model model, HttpSession session) {
        CarritoCompra carrito = (CarritoCompra) session.getAttribute("miCarrito");
        try {
            if (carrito == null) {
                carrito = new CarritoCompra();
            }

            if (carrito.getDetalleOrden() == null) {
                carrito.setDetalleOrden(Collections.emptyList());
            }
        } catch (Exception e) {
            return "redirect:/kefa/lista_categoria_productos";
        }

        session.setAttribute("miCarrito", carrito);

        model.addAttribute("carrito", carrito);
        return "vista_lista_CarritoCompra";
    }

    @PostMapping("/carrito/agregar")
    public String agregarProductoAlCarrito(@RequestParam("productoId") Integer productoId, HttpSession session,
            Model model, @RequestParam("cantidad") Integer cantidad) {
        /*
         * CarritoCompra miCarrito = (CarritoCompra) session.getAttribute("miCarrito");
         * 
         * if (miCarrito == null) {
         * miCarrito = new CarritoCompra();
         * }
         * 
         * CarritoCompra miCarrito = new CarritoCompra();
         */

        CarritoCompra miCarrito = (CarritoCompra) session.getAttribute("miCarrito");

        if (miCarrito == null) {
            // Intenta recuperar un carrito existente de la base de datos para el usuario
            // String nickname = (String) session.getAttribute("nickname");
            miCarrito = iCarritoCompraService.buscarPorId(miCarrito.getId());

            if (miCarrito == null) {
                // Si no hay un carrito existente, crea uno nuevo
                miCarrito = new CarritoCompra();
                // Aquí podrías establecer propiedades adicionales al carrito, como el usuario
                // asociado
            }
        } else {
            // Si miCarrito ya existe, verifica si necesita ser recuperado de la base de
            // datos para estar gestionado por Hibernate
            if (miCarrito.getId() != null) {
                // Recupera el carrito de la base de datos para asegurarte de que esté
                // gestionado por Hibernate
                miCarrito = iCarritoCompraService.buscarPorId(miCarrito.getId());
            }
            // Si el carrito no tiene un ID, significa que es nuevo y aún no está
            // persistido, así que puedes usarlo directamente

        }
        try {
            String nickname = (String) session.getAttribute("nickname");
            miCarrito = iCarritoCompraService.agregarProductoAlCarrito(productoId, nickname, cantidad, miCarrito);
            session.setAttribute("miCarrito", miCarrito);
            return "redirect:/kefa/lista_categoria_productos";
        } catch (Exception e) {
            model.addAttribute("error", "Error al agregar producto al carrito: " + e.getMessage());
            e.printStackTrace();
            return "vista_lista_CarritoCompra";
        }
    }

    @PostMapping("/carrito/eliminar/{id}")
    public String eliminarProductoDelCarrito(@PathVariable Integer id, HttpSession session,
            Model model) {
        CarritoCompra carritoCompra = (CarritoCompra) session.getAttribute("miCarrito");

        if (carritoCompra.getId() != null) {
            try {
                /*
                 * carritoCompra.getDetalleOrden().removeIf(detalle -> detalle.getId() == id);
                 * carritoCompra.getDetalleOrden().forEach(System.out::println);
                 */
                iCarritoCompraService.eliminar(carritoCompra, id);
            } catch (Exception e) {
                model.addAttribute("error", "Error al eliminar producto del carrito: " + e.getMessage());
            }
        }
        return "redirect:/kefa/carrito";
    }

}
