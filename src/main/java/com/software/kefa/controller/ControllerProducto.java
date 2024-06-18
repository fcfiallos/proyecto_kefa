package com.software.kefa.controller;

import java.util.function.Predicate;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.repository.modelo.Ubicacion;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.service.IProductoService;
import com.software.kefa.service.modelosto.ProductoTO;

@Controller
@RequestMapping("/kefa")
public class ControllerProducto {

    @Autowired
    private IProductoService iProductoService;

    @GetMapping("/lista_productos")
    public String vistaListaProductos(Model model) {
        List<Producto> productos = this.iProductoService.buscarTodo();
        model.addAttribute("productos", productos);
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
                && prod.getDescripcion().length() <= 250 && !prod.getDescripcion().isEmpty()
                && !prod.getImagen().isEmpty()
                && !prod.getNombre().isEmpty() && !prod.getNombreProveedor().isEmpty() && !prod.getPrecio().isEmpty();

        if (validar.test(productoTO)) {

            this.iProductoService.guardar(productoTO);
            return "redirect:/kefa/lista_productos";
        } else {
            model.addAttribute("error",
                    "Todos los campos son obligatorios y la descripción no debe exceder los 250 caracteres");
            return "formulario_producto";
        }
    }

    @GetMapping("/formulario_actualizar_producto/{codigo}")
    public String mostrarFormularioActualizarUsuaClie(@PathVariable("codigo") String codigo, Model model) {
        Producto producto = this.iProductoService.buscarPorCodigo(codigo);
        model.addAttribute("producto", producto);
        return "formulario_actua_producto";
    }

    @PutMapping("/actualizar_producto/{codigo}")
    public String mostrarFormularioActulizar(@PathVariable("codigo") String codigo, ProductoTO producto, Model model) {
        Producto prodAux = this.iProductoService.buscarPorCodigo(codigo);
        if (prodAux != null && prodAux.getCodigo().equals(codigo)) {
            prodAux.setCantidad(producto.getCantidad());
            BigDecimal precio = new BigDecimal(producto.getPrecio());
            prodAux.setPrecio(precio);
            prodAux.setNombre(producto.getNombre());
            prodAux.setImagen(producto.getImagen());
            prodAux.setDescripcion(producto.getDescripcion());
            this.iProductoService.actualizar(prodAux);
            return "redirect:/kefa/lista_productos";
        } else {
            model.addAttribute("error", "No se actualizaron los datos del producto, intente nuevamente");
            return "formulario_actua_producto";
        }
    }

}