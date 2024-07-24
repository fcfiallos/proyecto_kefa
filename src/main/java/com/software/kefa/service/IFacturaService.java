package com.software.kefa.service;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.Factura;
import com.software.kefa.repository.modelo.Orden;

/**
 * The IFacturaService interface provides methods for managing invoices.
 */
public interface IFacturaService {
    /**
     * Retrieves a factura (invoice) by its ID.
     *
     * @param id the ID of the factura to retrieve
     * @return the factura with the specified ID, or null if not found
     */
    public Factura buscarPorId(Integer id);

    /**
     * Saves a factura (invoice) to the database.
     *
     * @param factura the factura to save
     */
    public void guardar(Factura factura);

    /**
     * Updates a factura (invoice) in the database.
     *
     * @param factura the factura to update
     */
    public void actualizar(Factura factura);

    /**
     * Sends a factura (invoice) to the specified recipient.
     *
     * @param carritoCompra the carritoCompra (shopping cart) associated with the factura
     * @param nickname the nickname of the recipient
     * @param orden the orden (order) associated with the factura
     * @return the sent factura
     */
    public Factura enviarFactura(CarritoCompra carritoCompra, String nickname, Orden orden);
}
