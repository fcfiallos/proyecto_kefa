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

    /**
     * Retrieves a list of products based on the given category ID and renders the
     * view for the product list.
     *
     * @param categoriaId The ID of the category.
     * @param model       The model object used to pass data to the view.
     * @return The name of the view to be rendered.
     */
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

    /**
     * Displays the form for creating a new product.
     * 
     * @param categoriaId The ID of the category for which the product is being
     *                    created.
     * @param model       The model object used to pass data to the view.
     * @return The name of the view template for the product creation form.
     */
    @GetMapping("/categoria/{categoriaId}/lista_productos/formulario_producto")
    public String mostrarFormularioProducto(@PathVariable("categoriaId") Integer categoriaId, Model model) {
        model.addAttribute("categoriaId", categoriaId);
        model.addAttribute("productoTO", new ProductoTO());
        return "formulario_producto";
    }

    /**
     * Adds a new product to the specified category.
     *
     * @param categoriaId The ID of the category to add the product to.
     * @param productoTO  The product transfer object containing the product
     *                    details.
     * @param session     The HttpSession object for managing session data.
     * @param model       The Model object for adding attributes to the view.
     * @return The view name to render after adding the product.
     */
    @PostMapping("/categoria/{categoriaId}/lista_productos/formulario_producto/añadir")
    public String añadirProducto(@PathVariable("categoriaId") Integer categoriaId,
            @ModelAttribute("productoTO") ProductoTO productoTO,
            HttpSession session, Model model) {
        CategoriaProducto categoria = null;

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
                // Integer listaDeseosId = (Integer) session.getAttribute("listaDeseosId");
                // Integer carritoId = (Integer) session.getAttribute("carritoId");
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

    /**
     * Retrieves the form for updating a product based on its category ID and
     * product code.
     *
     * @param categoriaId The ID of the category to which the product belongs.
     * @param codigo      The code of the product to be updated.
     * @param model       The model object to be used for rendering the view.
     * @return The name of the view template for the update product form.
     */
    @GetMapping("/categoria/{categoriaId}/lista_productos/formulario_actualizar_producto/{codigo}")
    public String mostrarFormularioActualizarProducto(@PathVariable("categoriaId") Integer categoriaId,
            @PathVariable("codigo") String codigo, Model model) {
        Producto producto = this.iProductoService.buscarPorCodigo(codigo);
        model.addAttribute("producto", producto);
        model.addAttribute("categoriaId", categoriaId);
        return "formulario_actua_producto";
    }

    /**
     * Displays the form for updating a product.
     *
     * @param codigo      The code of the product to be updated.
     * @param categoriaId The ID of the category to which the product belongs.
     * @param producto    The updated product data.
     * @param model       The model object for rendering views.
     * @return A string representing the view to be displayed after updating the
     *         product.
     */
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

    /**
     * Handles the MethodArgumentTypeMismatchException by displaying an error
     * message and returning the "formulario_producto" view.
     * 
     * @param e     The MethodArgumentTypeMismatchException that occurred.
     * @param model The Model object used to pass data to the view.
     * @return The name of the view to be rendered, in this case,
     *         "formulario_producto".
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatchException(MethodArgumentTypeMismatchException e, Model model) {
        model.addAttribute("error",
                "El valor proporcionado para el ID no es válido. Por favor, intenta con un número.");
        return "formulario_producto"; // Reemplaza "error_page" con el nombre de tu vista de error
    }

}