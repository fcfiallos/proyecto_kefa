package com.software.kefa.controller;

import java.util.function.Predicate;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.repository.modelo.modelosdto.ProductoDTO;
import com.software.kefa.service.IProductoService;
import com.software.kefa.service.modelosto.ProductoTO;

@Controller
@RequestMapping("/kefa")
public class ControllerProducto {

    @Autowired
    private IProductoService iProductoService;

    @GetMapping("/lista_productos")
    public String vistaListaProductos(Model model) {
        List<ProductoDTO> productosDTO = this.iProductoService.buscarTodo();
        model.addAttribute("productosDTO", productosDTO);
        return "vista_lista_producto";
    }

    @GetMapping("/formulario_producto")
    public String mostrarFormularioProducto(Model model) {
        model.addAttribute("productoTO", new ProductoTO());
        return "formulario_producto";
    }

    @PostMapping("/añadir")
    public String añadirProducto(@ModelAttribute("productoTO") ProductoTO productoTO, Model model) {
        Predicate<ProductoTO> validar = prod -> prod.getCantidad() > 0 && !prod.getCodigo().isEmpty()
                && prod.getDescripcion().length() <= 250 && ! prod.getDescripcion().isEmpty()  && !prod.getImagen().isEmpty()
                && !prod.getNombre().isEmpty() && !prod.getNombreProveedor().isEmpty() && !prod.getPrecio().isEmpty();

        if (validar.test(productoTO)) {
            
            this.iProductoService.guardar(productoTO);
            return "redirect:/kefa/lista_productos"; 
        } else {
            model.addAttribute("error", "Todos los campos son obligatorios y la descripción no debe exceder los 250 caracteres");
            return "formulario_producto";
        }
    }

}