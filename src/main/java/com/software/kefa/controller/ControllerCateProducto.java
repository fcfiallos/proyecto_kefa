package com.software.kefa.controller;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.repository.modelo.CategoriaProducto;
import com.software.kefa.service.ICategoriaProductoService;

@Controller
@RequestMapping("/kefa")
public class ControllerCateProducto {

    @Autowired
    private ICategoriaProductoService categoriaProductoService;

    @GetMapping("/lista_categoria_productos")
    public String vistaListaProductos(Model model) {
        List<CategoriaProducto> categoriaProductos = this.categoriaProductoService.buscarTodo();
        model.addAttribute("categoriaProductos", categoriaProductos);
        return "vista_lista_cate_producto";
    }

    @GetMapping("/formulario_categoria_producto")
    public String mostrarFormularioProducto(Model model) {
        model.addAttribute("categoriaProducto", new CategoriaProducto());
        return "formulario_cate_producto";
    }

    @PostMapping("/registrar_categoria_producto")
    public String añadirProducto(@ModelAttribute("categoriaProducto") CategoriaProducto producto, Model model) {
        Predicate<CategoriaProducto> validar = prod -> !prod.getDescripcion().isEmpty() && !prod.getImagen().isEmpty() && !prod.getTipo().isEmpty();

        if (validar.test(producto)) {
            this.categoriaProductoService.guardar(producto);
            return "redirect:/kefa/lista_categoria_productos"; 
        } else {
            model.addAttribute("error", "Todos los campos son obligatorios y la descripción no debe exceder los 250 caracteres");
            return "formulario_cate_producto";
        }
    }

}