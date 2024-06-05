package com.software.kefa.controller;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.excepcion.UsuarioExisteExcepcion;
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.service.IProductoService;
import com.software.kefa.service.modelosto.ProductoTO;
import com.software.kefa.service.modelosto.UsuarioRegistroTO;

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
    public String añadirProducto(@ModelAttribute("productoTO") ProductoTO productoTO, Model model){
        Predicate <ProductoTO> validar = prod -> prod.getCantidad() != null  && !prod.getCodigo().isEmpty() && !prod.getDescripcion().isEmpty() && !prod.getEstado().isEmpty() && !prod.getImagen().isEmpty() && !prod.getNombre().isEmpty() && !prod.getNombreProveedor().isEmpty() && prod.getPrecio() != null ;
        
        if (validar.test(productoTO)) {
            this.iProductoService.guardar(productoTO);
                return "formulario_producto";
        } else {
            model.addAttribute("error", "Todos los campos son obligatorios");
            return "formulario_registro_ClieUsua";
        }
    }

}
