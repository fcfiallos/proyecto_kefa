package com.software.kefa.controller;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.software.kefa.repository.modelo.CategoriaProducto;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.service.ICategoriaProductoService;
import com.software.kefa.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

/**
 * This class is a controller for managing category products in the application.
 * It handles requests related to category products and interacts with the
 * category product service.
 */
@Controller
@RequestMapping("/kefa")
public class ControllerCateProducto {

    @Autowired
    private ICategoriaProductoService categoriaProductoService;

    @Autowired
    private IUsuarioService usuarioService;

    /**
     * Retrieves a list of category products and adds them to the model.
     * 
     * @param model the model object to add the category products to
     * @return the name of the view to render
     */
    @GetMapping("/lista_categoria_productos")
    public String vistaListaProductos(Model model) {
        List<CategoriaProducto> categoriaProductos = this.categoriaProductoService.buscarTodo();
        model.addAttribute("categoriaProductos", categoriaProductos);
        return "vista_lista_cate_producto";
    }

    /**
     * Retrieves a list of category products and adds them to the model.
     * 
     * @param model the model object to add the category products to
     * @return the name of the view to render
     */
    @GetMapping("/lista_categoria_productos/empleado")
    public String vistaListaCateProductosEmpl(Model model) {
        List<CategoriaProducto> categoriaProductos = this.categoriaProductoService.buscarTodo();
        model.addAttribute("categoriaProductos", categoriaProductos);
        return "vista_lista_cate_producto_empleado";
    }

    /**
     * Redirects the user to the view of products filtered by the selected category.
     *
     * @param id      the ID of the selected category
     * @param model   the model object to add attributes to
     * @param session the HttpSession object to store the category ID
     * @return the view name to redirect to
     */
    @GetMapping("/categoria/{categoriaId}/productos")
    public String redirigirProductosPorCategoria(@PathVariable("categoriaId") Integer id, Model model,
            HttpSession session) {
        // Capturar el ID de la categoría seleccionada
        session.setAttribute("categoriaId", id);
        CategoriaProducto categoriaProducto = this.categoriaProductoService.buscarPorId(id);

        if (categoriaProducto != null) {
            // Redirigir al usuario a la vista de productos filtrados por esa categoría
            return "redirect:/kefa/categoria/" + id + "/lista_productos";
        } else {
            // Manejar el caso cuando la categoría no se encuentra
            model.addAttribute("error", "Categoría no encontrada");
            return "redirect:/kefa/lista_categoria_productos";
        }
    }

    /**
     * Displays the formulario_cate_producto form.
     *
     * @param model the model object to be used for rendering the view
     * @return the name of the view to be rendered
     */
    @GetMapping("/formulario_categoria_producto")
    public String mostrarFormularioProducto(Model model) {
        model.addAttribute("categoriaProducto", new CategoriaProducto());
        return "formulario_cate_producto";
    }

    /**
     * Adds a new product category.
     *
     * @param producto The product category to be added.
     * @param model    The model object for rendering views.
     * @return The view name to redirect to after adding the product category.
     */
    @PostMapping("/registrar_categoria_producto")
    public String aniadirCateProducto(@ModelAttribute("categoriaProducto") CategoriaProducto producto, Model model,
            HttpSession session) {
        Predicate<CategoriaProducto> validar = prod -> !prod.getDescripcion().isEmpty() && !prod.getImagen().isEmpty()
                && !prod.getTipo().isEmpty();

        if (validar.test(producto)) {
            String nickname = (String) session.getAttribute("nickname");
            Usuario usuario = this.usuarioService.buscarPorNickname(nickname);

            if (usuario == null) {
                model.addAttribute("error", "Usuario no encontrado");
                return "redirect:/kefa/formulario_iniciar_sesion";
            }

            this.categoriaProductoService.guardar(producto);
            return "redirect:/kefa/lista_categoria_productos";
        } else {
            model.addAttribute("error",
                    "Todos los campos son obligatorios y la descripción no debe exceder los 250 caracteres");
            return "formulario_cate_producto";
        }
    }

}