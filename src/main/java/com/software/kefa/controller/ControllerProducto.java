package com.software.kefa.controller;

import java.math.BigDecimal;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.software.kefa.excepcion.MensajeExisteExcepcion;
import com.software.kefa.repository.modelo.CategoriaProducto;
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.service.ICategoriaProductoService;
import com.software.kefa.service.IProductoService;
import com.software.kefa.service.modelosto.ProductoTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/kefa")
public class ControllerProducto {

    private static final Logger logger = LoggerFactory.getLogger(ControllerProducto.class);

    @Autowired
    private IProductoService iProductoService;

    @Autowired
    private ICategoriaProductoService categoriaProductoService;

    @GetMapping("/categoria/{id}/productos")
    public String vistaProductosPorCategoria(@PathVariable("id") Integer categoriaId, Model model) {
        logger.info("vistaProductosPorCategoria: categoriaId={}", categoriaId);
        List<Producto> productos = this.categoriaProductoService.buscarProductosPorCategoria(categoriaId);
        CategoriaProducto categoria = this.categoriaProductoService.buscarPorId(categoriaId);
        model.addAttribute("productos", productos);
        model.addAttribute("categoria", categoria);
        return "vista_lista_producto";
    }

    @GetMapping("/formulario_producto")
    public String mostrarFormularioProducto(@RequestParam("categoriaId") Integer categoriaId, Model model) {
        logger.info("mostrarFormularioProducto: categoriaId={}", categoriaId);
        model.addAttribute("productoTO", new ProductoTO());
        model.addAttribute("categoriaId", categoriaId);
        return "formulario_producto";
    }

    @PostMapping("/añadir")
    public String añadirProducto(@RequestParam("categoriaId") Integer categoriaId, @ModelAttribute("productoTO") ProductoTO productoTO, Model model) {
        logger.info("añadirProducto: categoriaId={}", categoriaId);
        Predicate<ProductoTO> validar = prod -> prod.getCantidad() > 0 && !prod.getCodigo().isEmpty()
                && prod.getDescripcion().length() <= 250 && !prod.getDescripcion().isEmpty()
                && !prod.getImagen().isEmpty()
                && !prod.getNombre().isEmpty() && !prod.getNombreProveedor().isEmpty() && !prod.getPrecio().isEmpty();

        if (validar.test(productoTO)) {
            try {
                this.iProductoService.guardar(productoTO);
                return "redirect:/kefa/categoria/" + categoriaId + "/productos"; 
            } catch (MensajeExisteExcepcion e) {
                model.addAttribute("error", e.getMessage());
                model.addAttribute("categoriaId", categoriaId); // Add this line to pass categoriaId back to the form
                return "formulario_producto";
            }
        } else {
            model.addAttribute("error",
                    "Todos los campos son obligatorios y la descripción no debe exceder los 250 caracteres");
            model.addAttribute("categoriaId", categoriaId); // Add this line to pass categoriaId back to the form
            return "formulario_producto";
        }
    }

    @GetMapping("/formulario_actualizar_producto/{codigo}")
    public String mostrarFormularioActualizarUsuaClie(@PathVariable("codigo") String codigo, @RequestParam("categoriaId") Integer categoriaId, Model model) {
        logger.info("mostrarFormularioActualizarUsuaClie: codigo={}, categoriaId={}", codigo, categoriaId);
        Producto producto = this.iProductoService.buscarPorCodigo(codigo);
        model.addAttribute("producto", producto);
        model.addAttribute("categoriaId", categoriaId); // Add this line to pass categoriaId to the form
        return "formulario_actua_producto";
    }

    @PutMapping("/actualizar_producto/{codigo}")
    public String actualizarProducto(@PathVariable("codigo") String codigo, @RequestParam("categoriaId") Integer categoriaId, @ModelAttribute("productoTO") ProductoTO producto, Model model) {
        logger.info("actualizarProducto: codigo={}, categoriaId={}", codigo, categoriaId);
        Producto prodAux = this.iProductoService.buscarPorCodigo(codigo);
        if (prodAux != null && prodAux.getCodigo().equals(codigo)) {
            prodAux.setCantidad(producto.getCantidad());
            BigDecimal precio = new BigDecimal(producto.getPrecio());
            prodAux.setPrecio(precio);
            prodAux.setNombre(producto.getNombre());
            prodAux.setImagen(producto.getImagen());
            prodAux.setDescripcion(producto.getDescripcion());
            this.iProductoService.actualizar(prodAux);
            return "redirect:/kefa/categoria/" + categoriaId + "/productos";
        } else {
            model.addAttribute("error", "No se actualizaron los datos del producto, intente nuevamente");
            model.addAttribute("categoriaId", categoriaId); // Add this line to pass categoriaId back to the form
            return "formulario_actua_producto";
        }
    }
}
