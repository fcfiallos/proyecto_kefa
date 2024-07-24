package com.software.kefa.repository;

import com.software.kefa.repository.modelo.Pago;

/**
 * The IMetodoPagoRepository interface represents a repository for managing payment methods.
 */
public interface IMetodoPagoRepository {
    
    /**
     * Inserts a new payment into the repository.
     *
     * @param pago The payment to be inserted.
     */
    public void insertar(Pago pago);
    
    /**
     * Updates an existing payment in the repository.
     *
     * @param pago The payment to be updated.
     */
    public void actualizar(Pago pago);
    
    /**
     * Deletes a payment from the repository.
     *
     * @param pago The payment to be deleted.
     */
    public void eliminar(Pago pago);
    
    /**
     * Retrieves a payment from the repository based on its ID.
     *
     * @param id The ID of the payment to be retrieved.
     * @return The payment with the specified ID, or null if not found.
     */
    public Pago seleccionarPorId(Integer id);
}

