package com.software.kefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.service.IClienteService;

@Controller
@RequestMapping ("/kefa")
public class ControllerCliente {
    @Autowired
    private IClienteService clienteService;

    
}
