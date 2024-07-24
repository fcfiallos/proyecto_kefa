package com.software.kefa.service;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Orden;

/**
 * The IOrdenService interface provides methods for managing orders.
 */
public interface IOrdenService {
    
    /**
     * Saves the given order.
     * 
     * @param orden The order to be saved.
     */
    public void guardar(Orden orden);
    
    /**
     * Updates the given order.
     * 
     * @param orden The order to be updated.
     */
    public void actualizar(Orden orden);
    
    /**
     * Retrieves the order with the specified ID.
     * 
     * @param id The ID of the order to be retrieved.
     * @return The order with the specified ID, or null if not found.
     */
    public Orden buscarPorId(Integer id);
    
    /**
     * Creates an order payment using the given nickname and shopping cart.
     * 
     * @param nickname The nickname of the user placing the order.
     * @param carrito The shopping cart containing the items to be ordered.
     * @return The created order payment.
     */
    public Orden crearOrdenDePago(String nickname, CarritoCompra carrito);
}
