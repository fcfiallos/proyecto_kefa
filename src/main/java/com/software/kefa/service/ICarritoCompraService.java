package com.software.kefa.service;

import java.util.List;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Producto;

/**
 * This interface represents the service for managing shopping carts.
 */
public interface ICarritoCompraService {
    
    /**
     * Saves the given shopping cart.
     * 
     * @param carritoCompra The shopping cart to be saved.
     */
    public void guardar(CarritoCompra carritoCompra);

    /**
     * Updates the given shopping cart with the specified detail ID.
     * 
     * @param carritoCompra The shopping cart to be updated.
     * @param detalleId The ID of the detail to be updated.
     * @return The updated shopping cart.
     */
    public CarritoCompra actualizar(CarritoCompra carritoCompra, Integer detalleId);

    /**
     * Retrieves the shopping cart with the specified ID.
     * 
     * @param id The ID of the shopping cart to be retrieved.
     * @return The shopping cart with the specified ID, or null if not found.
     */
    public CarritoCompra buscarPorId(Integer id);

    /**
     * Retrieves all products in the shopping cart with the specified ID.
     * 
     * @param idCarritoCompra The ID of the shopping cart.
     * @return A list of products in the shopping cart.
     */
    public List<Producto> buscarTodo(Integer idCarritoCompra);

    /**
     * Adds a product to the shopping cart.
     * 
     * @param productoId The ID of the product to be added.
     * @param nickname The nickname of the user.
     * @param cantidad The quantity of the product to be added.
     * @param carritoCompra The shopping cart to which the product will be added.
     * @return The updated shopping cart.
     */
    public CarritoCompra agregarProductoAlCarrito(Integer productoId, String nickname, Integer cantidad, CarritoCompra carritoCompra);

}
