package com.software.kefa.controller;

import java.io.IOException;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.service.IProductoService;
import com.software.kefa.service.modelosto.ProductoTO;

@Controller
@RequestMapping("/kefa")
public class ControllerProducto {

    @Autowired
    private IProductoService iProductoService;

    @GetMapping("/formulario_producto")
    public String mostrarFormularioProducto(Model model) {
        model.addAttribute("productoTO", new ProductoTO());
        return "formulario_producto";
    }

    @PostMapping("/añadir")
    public String añadirProducto(@ModelAttribute("productoTO") ProductoTO productoTO, Model model) {
        Predicate<ProductoTO> validar = prod -> prod.getCantidad() != null && !prod.getCodigo().isEmpty()
                && !prod.getDescripcion().isEmpty() && !prod.getEstado().isEmpty() && prod.getImagen() != null
                && !prod.getNombre().isEmpty() && !prod.getNombreProveedor().isEmpty() && prod.getPrecio() != null && prod.getImagenByte() != null;

        if (validar.test(productoTO)) {
            try {
                byte[] imagenBytes = productoTO.getImagen().getBytes();
                productoTO.setImagenBytes(imagenBytes);
            } catch (IOException e) {
                model.addAttribute("error", "Error al procesar la imagen");
                return "formulario_producto"; // Corrected the return view name
            }
            this.iProductoService.guardar(productoTO);
            return "formulario_producto"; // This view might be incorrect. Adjust as necessary.
        } else {
            model.addAttribute("error", "Todos los campos son obligatorios");
            return "formulario_producto"; // Corrected the return view name
        }
    }

}