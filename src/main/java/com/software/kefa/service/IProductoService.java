package com.software.kefa.service;

import java.util.List;
 
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.service.modelosto.ProductoTO;
 
/**
 * This interface represents the service for managing products.
 */
public interface IProductoService {
    
    /**
     * Retrieves a list of products based on the given category ID.
     *
     * @param categoriaID the ID of the category
     * @return a list of products matching the category ID
     */
    public List<Producto> buscarPorCategoriaId(Integer categoriaID);
    
    /**
     * Retrieves a list of all products.
     *
     * @return a list of all products
     */
    public List<Producto> buscarTodo();
    
    /**
     * Saves a new product with the given details.
     *
     * @param producto the product details
     * @param nickname the nickname of the user saving the product
     */
    public void guardar(ProductoTO producto, String nickname);
    
    /**
     * Updates an existing product with the given details.
     *
     * @param producto the updated product details
     */
    public void actualizar(Producto producto);
    
    /**
     * Checks if a product with the given code already exists.
     *
     * @param codigo the product code to check
     * @return true if a product with the code exists, false otherwise
     */
    public boolean existeProductoCodigo(String codigo);
    
    /**
     * Checks if a supplier with the given name already exists.
     *
     * @param nombre the supplier name to check
     * @return true if a supplier with the name exists, false otherwise
     */
    public boolean existeProveedorNombre(String nombre);
    
    /**
     * Retrieves a product based on the given code.
     *
     * @param codigo the product code
     * @return the product with the matching code, or null if not found
     */
    public Producto buscarPorCodigo(String codigo);
    
    /**
     * Retrieves a product based on the given ID.
     *
     * @param id the product ID
     * @return the product with the matching ID, or null if not found
     */
    public Producto buscarPorId(Integer id);
    
    /**
     * Updates the stock of a product with the given ID.
     *
     * @param productoId the ID of the product
     * @param cantidad the new stock quantity
     */
    public void actualizarStock(Integer productoId, Integer cantidad);
}