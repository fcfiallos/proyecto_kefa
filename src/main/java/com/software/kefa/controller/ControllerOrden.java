package com.software.kefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.excepcion.MensajeExisteExcepcion;
import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Orden;
import com.software.kefa.service.IOrdenService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kefa")
public class ControllerOrden {

    @Autowired
    private IOrdenService ordenPagoService;

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
                }
            }
            return "vista_ordenPago";
        } catch (MensajeExisteExcepcion e) {
            model.addAttribute("error", e.getMessage());
            e.printStackTrace();
            return "vista_ordenPago";
        }
    }
}
