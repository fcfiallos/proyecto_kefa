package com.software.kefa.repository;


import java.util.List;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.DetalleOrden;
import com.software.kefa.repository.modelo.Producto;

/**
 * The ICarritoCompraRepository interface represents a repository for managing carritoCompra objects.
 */
public interface ICarritoCompraRepository {
    
    /**
     * Inserts a new carritoCompra into the repository.
     * 
     * @param carritoCompra The carritoCompra object to insert.
     */
    public void insertar(CarritoCompra carritoCompra);
    
    /**
     * Updates an existing carritoCompra in the repository.
     * 
     * @param carritoCompra The carritoCompra object to update.
     */
    public void actualizar(CarritoCompra carritoCompra);
    
    /**
     * Retrieves a carritoCompra by its ID from the repository.
     * 
     * @param id The ID of the carritoCompra to retrieve.
     * @return The carritoCompra object with the specified ID, or null if not found.
     */
    public CarritoCompra seleccionarPorId(Integer id);
    
    /**
     * Retrieves all productos associated with a carritoCompra from the repository.
     * 
     * @param id The ID of the carritoCompra.
     * @return A list of productos associated with the carritoCompra.
     */
    public List<Producto> seleccionarTodo(Integer id);
    
    /**
     * Retrieves a carritoCompra by the nickname of the associated usuario from the repository.
     * 
     * @param nickname The nickname of the usuario.
     * @return The carritoCompra object associated with the specified nickname, or null if not found.
     */
    public CarritoCompra seleccionarPorUsuarioNickname(String nickname);
    
    /**
     * Retrieves all detalleOrden objects associated with a carritoCompra from the repository.
     * 
     * @param id The ID of the carritoCompra.
     * @return A list of detalleOrden objects associated with the carritoCompra.
     */
    public List<DetalleOrden> seleccionarDetalleOrdenPorCarritoCompraId(Integer id);
    
    /**
     * Deletes a carritoCompra from the repository.
     * 
     * @param carritoCompra The carritoCompra object to delete.
     */
    public void eliminar(Integer carritoCompra);
    
    /**
     * Retrieves all carritoCompra objects associated with a usuario's nickname from the repository.
     * 
     * @param nickname The nickname of the usuario.
     * @return A list of carritoCompra objects associated with the specified nickname.
     */
    public List<CarritoCompra> seleccionarPorNickname(String nickname);
    
    /**
     * Combines two carritoCompra objects into a single carritoCompra.
     * 
     * @param carritoCompra The carritoCompra object to combine with.
     * @return The combined carritoCompra object.
     */
    public CarritoCompra unir(CarritoCompra carritoCompra);
}
