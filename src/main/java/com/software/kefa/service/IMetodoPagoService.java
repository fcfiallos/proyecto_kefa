package com.software.kefa.service;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Orden;
import com.software.kefa.repository.modelo.Pago;
import com.software.kefa.service.modelosto.MetodoPagoTO;

/**
 * This interface represents the service for managing payment methods.
 */
public interface IMetodoPagoService {
    
    /**
     * Saves a payment.
     * 
     * @param pago The payment to be saved.
     */
    public void guardar(Pago pago);

    /**
     * Sends a payment method for validation.
     * 
     * @param metodoPagoTO The payment method transfer object.
     * @param nickname The user's nickname.
     * @param carritoCompra The shopping cart.
     * @param orden The order.
     */
    public void enviarValidacion(MetodoPagoTO metodoPagoTO, String nickname, CarritoCompra carritoCompra, Orden orden);

    /**
     * Deletes a payment.
     * 
     * @param pago The payment to be deleted.
     */
    public void eliminar(Pago pago);

    /**
     * Searches for a payment by its ID.
     * 
     * @param id The ID of the payment.
     * @return The payment with the specified ID, or null if not found.
     */
    public Pago buscarPorId(Integer id);

    /**
     * Updates a payment.
     * 
     * @param pago The payment to be updated.
     */
    public void actualizar(Pago pago);
}
