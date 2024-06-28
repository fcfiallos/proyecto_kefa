package com.software.kefa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.software.kefa.excepcion.MensajeExisteExcepcion;
import com.software.kefa.repository.modelo.CategoriaProducto;
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.service.ICategoriaProductoService;
import com.software.kefa.service.IProductoService;
import com.software.kefa.service.modelosto.ProductoTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kefa")
public class ControllerProducto {

    @Autowired
    private IProductoService iProductoService;

    @Autowired
    private ICategoriaProductoService iCategoriaProductoService;

    @GetMapping("/categoria/{categoriaId}/lista_productos")
    public String vistaListaProductosPorCategoria(@PathVariable("categoriaId") Integer categoriaId, Model model) {
        // Buscar todos los productos relacionados con la categoría dada
        List<Producto> productos = this.iProductoService.buscarPorCategoriaId(categoriaId);
        CategoriaProducto categoria = this.iCategoriaProductoService.buscarPorId(categoriaId);
        model.addAttribute("categoriaId", categoriaId);
        model.addAttribute("productos", productos);
        model.addAttribute("categoria", categoria);
        return "vista_lista_producto";
    }

    @GetMapping("/categoria/{categoriaId}/lista_productos/formulario_producto")
    public String mostrarFormularioProducto(@PathVariable("categoriaId") Integer categoriaId, Model model) {
        model.addAttribute("categoriaId", categoriaId);
        model.addAttribute("productoTO", new ProductoTO());
        return "formulario_producto";
    }

    @PostMapping("/categoria/{categoriaId}/lista_productos/formulario_producto/añadir")
    public String añadirProducto(@PathVariable("categoriaId") Integer categoriaId,@ModelAttribute("productoTO") ProductoTO productoTO,
            HttpSession session, Model model) {
        CategoriaProducto categoria = null;

        /*if (productoTO.getCategoriaId() == null) {
            model.addAttribute("error", "El ID de la categoría es requerido.");
            return "formulario_producto";
        }
*/
        try {
            categoria = this.iCategoriaProductoService.buscarPorId(categoriaId);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "formulario_producto";
        }
        Predicate<ProductoTO> validar = prod -> prod.getCantidad() > 0 && !prod.getCodigo().isEmpty()
                && prod.getDescripcion().length() <= 250 && !prod.getDescripcion().isEmpty()
                && !prod.getImagen().isEmpty()
                && !prod.getNombre().isEmpty() && !prod.getNombreProveedor().isEmpty() && !prod.getPrecio().isEmpty();

        if (validar.test(productoTO)) {

            try {
                String nickname = (String) session.getAttribute("nickname");
                this.iProductoService.guardar(productoTO, nickname);
                return "redirect:/kefa/categoria/" + categoria.getId() + "/lista_productos";
            } catch (MensajeExisteExcepcion e) {
                model.addAttribute("error", e.getMessage());
                return "formulario_producto";
            } catch (Exception e) {
                // Manejo de cualquier otra excepción no capturada específicamente
                model.addAttribute("error", "Ocurrió un error al guardar el producto: " + e.getMessage());
                return "formulario_producto";
            }
        } else {
            model.addAttribute("error",
                    "Validación del producto falló.");
            return "formulario_producto";
        }
    }

    @GetMapping("/categoria/{categoriaId}/lista_productos/formulario_actualizar_producto/{codigo}")
    public String mostrarFormularioActualizarProducto(@PathVariable("categoriaId") Integer categoriaId,
            @PathVariable("codigo") String codigo, Model model) {
        Producto producto = this.iProductoService.buscarPorCodigo(codigo);
        model.addAttribute("producto", producto);
        model.addAttribute("categoriaId", categoriaId);
        return "formulario_actua_producto";
    }

    @PutMapping("/categoria/{categoriaId}/lista_productos/actualizar_producto/{codigo}")
    public String mostrarFormularioActulizar(@PathVariable("codigo") String codigo,
            @PathVariable("categoriaId") Integer categoriaId, ProductoTO producto, Model model) {
        Producto prodAux = this.iProductoService.buscarPorCodigo(codigo);
        if (prodAux != null && prodAux.getCodigo().equals(codigo)) {
            prodAux.setCantidad(producto.getCantidad());
            BigDecimal precio = new BigDecimal(producto.getPrecio());
            prodAux.setPrecio(precio);
            prodAux.setNombre(producto.getNombre());
            prodAux.setImagen(producto.getImagen());
            prodAux.setDescripcion(producto.getDescripcion());
            return "redirect:/kefa/categoria/" + categoriaId + "/lista_productos";
        } else {
            model.addAttribute("error", "No se actualizaron los datos del producto, intente nuevamente");
            return "formulario_actua_producto";
        }
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatchException(MethodArgumentTypeMismatchException e, Model model) {
        model.addAttribute("error",
                "El valor proporcionado para el ID no es válido. Por favor, intenta con un número.");
        return "formulario_producto"; // Reemplaza "error_page" con el nombre de tu vista de error
    }

}