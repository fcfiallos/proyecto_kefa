package com.software.kefa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kefa")
public class ControllerMetodoPago {

    @GetMapping("/carrito/metodo_pago")
    public String redireccionarMetodoPago() {
        return "vista_metodo_pago";
    }
}
