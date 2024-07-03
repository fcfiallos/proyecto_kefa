package com.software.kefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.service.IPromocionService;

@Controller
@RequestMapping("/kefa")
public class ControllerPromocion {

    @Autowired
    private IPromocionService promocionService;
}
