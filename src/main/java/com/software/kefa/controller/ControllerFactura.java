package com.software.kefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.excepcion.MensajeExisteExcepcion;
import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Factura;
import com.software.kefa.repository.modelo.Orden;
import com.software.kefa.service.ICarritoCompraService;
import com.software.kefa.service.IFacturaService;
import com.software.kefa.service.IOrdenService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kefa")
public class ControllerFactura {
    @Autowired
    private IFacturaService facturaService;
    @Autowired
    private ICarritoCompraService carritoCompraService;
    @Autowired
    private IOrdenService ordenService;

    @GetMapping("/carrito/factura")
    public String redireccionarFactura(HttpSession session, Model model) {
        String nickname = (String) session.getAttribute("nickname");
        CarritoCompra carrito = (CarritoCompra) session.getAttribute("miCarrito");
        Orden orden = (Orden) session.getAttribute("miOrden");

        try {
            Factura factura = this.facturaService.enviarFactura(carrito, nickname, orden);
            model.addAttribute("factura", factura);
            return "vista_factura";
        } catch (MensajeExisteExcepcion e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/kefa/carrito";
        } catch (Exception e) {
            return "redirect:/kefa/carrito/metodo_pago";
        }
    }

    @PostMapping("/carrito/finalizar")
    public String finalizarSesionCarritoOrden(HttpSession session, @ModelAttribute("factura") Factura factura,
            Model model) {
        try {
            //CarritoCompra carritoCompra = (CarritoCompra) session.getAttribute("miCarrito");
            //this.carritoCompraService.eliminar(carritoCompra);
            session.removeAttribute("miCarrito");

            //Orden orden = (Orden) session.getAttribute("miOrden");  

            session.removeAttribute("miOrden");
            return "redirect:/kefa/lista_categoria_productos";
        } catch (Exception e) {
            model.addAttribute("error", "Error al finalizar la sesión del carrito y la orden");
            return "vista_factura";
        } finally {
            session.removeAttribute("miCarrito");
            session.removeAttribute("miOrden");
        }

    }
}
