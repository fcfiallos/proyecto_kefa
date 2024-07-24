package com.software.kefa.repository;

import java.util.List;

import com.software.kefa.repository.modelo.DetalleOrden;
import com.software.kefa.repository.modelo.ListaDeseos;
import com.software.kefa.repository.modelo.Producto;

/**
 * The IListaDeseoRepository interface represents a repository for managing wish lists.
 */
public interface IListaDeseoRepository {
    
    /**
     * Inserts a wish list into the repository.
     *
     * @param listaDeseo The wish list to insert.
     */
    public void insertar(ListaDeseos listaDeseo);
    
    /**
     * Updates a wish list in the repository.
     *
     * @param listaDeseo The wish list to update.
     */
    public void actualizar(ListaDeseos listaDeseo);
    
    /**
     * Retrieves all products from a wish list.
     *
     * @param id The ID of the wish list.
     * @return A list of products in the wish list.
     */
    public List<Producto> seleccionarTodo(Integer id);
    
    /**
     * Retrieves a wish list by its ID.
     *
     * @param id The ID of the wish list.
     * @return The wish list with the specified ID, or null if not found.
     */
    public ListaDeseos seleccionarPorId(Integer id);
    
    /**
     * Retrieves a wish list by the user's nickname.
     *
     * @param nickname The nickname of the user.
     * @return The wish list associated with the user's nickname, or null if not found.
     */
    public ListaDeseos seleccionarPorUsuarioNickname(String nickname);
    
    /**
     * Removes a product from a wish list.
     *
     * @param detalleId The ID of the product detail.
     * @param nickname The nickname of the user.
     */
    public void eliminarProductoDeLista(Integer detalleId, String nickname);
    
    /**
     * Retrieves the order details associated with a wish list.
     *
     * @param id The ID of the wish list.
     * @return A list of order details associated with the wish list.
     */
    public List<DetalleOrden> seleccionarDetalleOrdenPorListaDeseoId(Integer id);
}
