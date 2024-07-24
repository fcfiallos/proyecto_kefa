package com.software.kefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Orden;
import com.software.kefa.service.IOrdenService;

import jakarta.servlet.http.HttpSession;

/**
 * This class is a controller for handling order-related operations in the KEFA
 * application.
 */
@Controller
@RequestMapping("/kefa")
public class ControllerOrden {

    @Autowired
    private IOrdenService ordenPagoService;

    /**
     * Redirects to the order payment page.
     * 
     * @param session the HttpSession object for storing session attributes
     * @param model   the Model object for adding attributes to the view
     * @return the name of the view to be rendered
     */
    @GetMapping("/carrito/ordenPago")
    public String redireccionarOrdenPago(HttpSession session, Model model) {
        String nickname = (String) session.getAttribute("nickname");
        CarritoCompra carrito = (CarritoCompra) session.getAttribute("miCarrito");
        // Orden orden = carrito.getDetalleOrden().get(0).getOrden();
        try {
            if (carrito != null) {
                Orden orden = ordenPagoService.crearOrdenDePago(nickname, carrito);
                if (orden != null) {
                    session.setAttribute("miOrden", orden);
                    model.addAttribute("orden", orden);
                } else {
                    model.addAttribute("error", "Error al crear la orden de pago");
                }
            } else {
                model.addAttribute("error", "Error al crear la orden de pago, carrito vacío");
            }
            return "vista_ordenPago";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            e.printStackTrace();
            return "vista_ordenPago";
        }
    }
}
