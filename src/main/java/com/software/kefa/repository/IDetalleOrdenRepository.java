package com.software.kefa.repository;

import com.software.kefa.repository.modelo.DetalleOrden;

/**
 * This interface represents a repository for managing `DetalleOrden` objects.
 */
public interface IDetalleOrdenRepository {
    
    /**
     * Retrieves a `DetalleOrden` object by its ID.
     *
     * @param id the ID of the `DetalleOrden` to retrieve
     * @return the `DetalleOrden` object with the specified ID, or null if not found
     */
    public DetalleOrden seleccionarPorId(Integer id);
    
    /**
     * Retrieves a `DetalleOrden` object by the ID of its associated carrito.
     *
     * @param idCarrito the ID of the associated carrito
     * @return the `DetalleOrden` object with the specified carrito ID, or null if not found
     */
    public DetalleOrden seleccionarPorIdCarrito(Integer idCarrito);
    
    /**
     * Inserts a new `DetalleOrden` object into the repository.
     *
     * @param detalleOrden the `DetalleOrden` object to insert
     */
    public void insertar(DetalleOrden detalleOrden);
    
    /**
     * Updates an existing `DetalleOrden` object in the repository.
     *
     * @param detalleOrden the `DetalleOrden` object to update
     */
    public void actualizar(DetalleOrden detalleOrden);
    
    /**
     * Deletes a `DetalleOrden` object from the repository.
     *
     * @param detalleOrden the `DetalleOrden` object to delete
     */
    public void eliminar(DetalleOrden detalleOrden);
    
    /**
     * Saves or updates a `DetalleOrden` object in the repository.
     *
     * @param detalleOrden the `DetalleOrden` object to save or update
     * @return the saved or updated `DetalleOrden` object
     */
    public DetalleOrden guardarOActualizarDetalleOrden(DetalleOrden detalleOrden);
}
