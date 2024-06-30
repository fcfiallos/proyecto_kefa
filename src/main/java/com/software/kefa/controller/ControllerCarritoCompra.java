package com.software.kefa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.CategoriaProducto;
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.service.ICarritoCompraService;
import com.software.kefa.service.ICategoriaProductoService;
import com.software.kefa.service.IProductoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kefa")
public class ControllerCarritoCompra {
    @Autowired
    private ICarritoCompraService iCarritoCompraService;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private ICategoriaProductoService iCategoriaProductoService;

    /*@GetMapping("/categoria/{categoriaId}/lista_productos/carrito")
    public String vistaListaProductosPorCategoria(@PathVariable("categoriaId") Integer categoriaId, Model model) {
        // Buscar todos los productos relacionados con la categoría dada
        List<Producto> productos = this.productoService.buscarPorCategoriaId(categoriaId);
        CategoriaProducto categoria = this.iCategoriaProductoService.buscarPorId(categoriaId);
        model.addAttribute("categoriaId", categoriaId);
        model.addAttribute("productos", productos);
        model.addAttribute("categoria", categoria);
        return "vista_lista_producto";
    }*/

    @PostMapping("/categoria/{categoriaId}/lista_productos/carrito/agregarProducto")
    public String agregarProducto(@PathVariable("categoriaId") Integer categoriaId, @RequestParam Integer carritoId,
            @RequestParam Integer productoId, Model model,
            HttpSession session) {
        CategoriaProducto categoria = null;

        try {
            categoria = this.iCategoriaProductoService.buscarPorId(categoriaId);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "formulario_producto";
        }

        Producto producto = productoService.buscarPorId(productoId);
        try {
            String nickname = (String) session.getAttribute("nickname");
            iCarritoCompraService.agregarProducto(carritoId, producto, nickname);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "redirect:/categoria/"+categoriaId+"/lista_productos";
    }

    @PostMapping("/categoria/{categoriaId}/lista_productos/carrito/eliminarProducto")
    public String eliminarProducto(@PathVariable("categoriaId") Integer categoriaId ,@RequestParam Integer carritoId, @RequestParam Integer productoId, Model model) {
        Producto producto = productoService.buscarPorId(productoId);

        CategoriaProducto categoria = null;
        try {
            categoria = this.iCategoriaProductoService.buscarPorId(categoriaId);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "formulario_producto";
        }

        try {
            iCarritoCompraService.eliminarProducto(carritoId, producto);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/categoria/"+categoriaId+"/lista_productos";
    }

    @GetMapping("/carrito/ver/{id}")
    public String verCarrito(@PathVariable("id") Integer id, Model model) {
        CarritoCompra carrito = iCarritoCompraService.obtenerCarritoPorId(id);
        if (carrito == null) {
            model.addAttribute("error", "El carrito de compra no existe.");
        }
        model.addAttribute("carrito", carrito);
        return "vista_carrito_compra";
    }

}
