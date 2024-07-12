package com.software.kefa.controller;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.excepcion.MensajeExisteExcepcion;
import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Orden;
import com.software.kefa.service.IMetodoPagoService;
import com.software.kefa.service.modelosto.MetodoPagoTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kefa")
public class ControllerMetodoPago {

    @Autowired
    private IMetodoPagoService metodoPagoService;

    @GetMapping("/carrito/metodo_pago")
    public String redireccionarMetodoPago(Model model) {
        model.addAttribute("metodoPago", new MetodoPagoTO());
        return "vista_metodo_pago";
    }

    @PostMapping("/carrito/metodo_pago/enviar")
    public String enviarMetodoPago(@ModelAttribute("metodoPago") MetodoPagoTO metodoPagoTO, Model model,
            HttpSession session) {
        Predicate<MetodoPagoTO> validarTarjeta = tarjeta -> !tarjeta.getNumeroTarjeta().isEmpty()
                && !tarjeta.getNombreTarjeta().isEmpty() && !tarjeta.getFechaVencimiento().isEmpty()
                && !tarjeta.getCvv().isEmpty();
        if (validarTarjeta.test(metodoPagoTO)) {
            try {
                String nickname = (String) session.getAttribute("nickname");
                CarritoCompra carritoCompra = (CarritoCompra) session.getAttribute("miCarrito");
                Orden orden = (Orden) session.getAttribute("miOrden");
                this.metodoPagoService.enviarValidacion(metodoPagoTO, nickname, carritoCompra, orden);
                return "redirect:/kefa/carrito/factura";
            } catch (MensajeExisteExcepcion e) {
                model.addAttribute("error", e.getMessage());
                return "vista_metodo_pago";
            } catch (Exception e) {
                model.addAttribute("error", "Error al procesar la transacción");
                return "vista_metodo_pago";
            }
        } else {
            model.addAttribute("error", "Datos de tarjeta inválidos");
            return "vista_metodo_pago";
        }

    }
}
