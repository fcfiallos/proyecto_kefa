package com.software.kefa.service;

import java.util.List;

import com.software.kefa.repository.modelo.DetalleOrden;
import com.software.kefa.repository.modelo.ListaDeseos;
import com.software.kefa.repository.modelo.Producto;

/**
 * The IListaDeseoService interface provides methods for managing wish lists.
 */
public interface IListaDeseoService {
    
    /**
     * Saves a wish list.
     *
     * @param listaDeseo The wish list to be saved.
     */
    public void guardar(ListaDeseos listaDeseo);
    
    /**
     * Updates a wish list.
     *
     * @param listaDeseo The wish list to be updated.
     */
    public void actualizar(ListaDeseos listaDeseo);
    
    /**
     * Retrieves all products in a wish list.
     *
     * @param id The ID of the wish list.
     * @return A list of products in the wish list.
     */
    public List<Producto> buscarTodo(Integer id);
    
    /**
     * Retrieves all order details for a given wish list.
     *
     * @param id The ID of the wish list.
     * @return A list of order details for the wish list.
     */
    public List<DetalleOrden> buscarDetallePorIdLista(Integer id);
    
    /**
     * Retrieves a wish list by its ID.
     *
     * @param id The ID of the wish list.
     * @return The wish list with the specified ID.
     */
    public ListaDeseos buscarPorId(Integer id);
    
    /**
     * Adds a product to a wish list.
     *
     * @param productoId The ID of the product to be added.
     * @param nickname The nickname of the user.
     * @param listaDeseo The wish list to which the product will be added.
     * @return The updated wish list with the added product.
     */
    public ListaDeseos agregarProductoALaLista(Integer productoId, String nickname, ListaDeseos listaDeseo);
    
    /**
     * Removes a product from a wish list.
     *
     * @param detalleOrdenId The ID of the order detail representing the product in the wish list.
     * @param nickname The nickname of the user.
     */
    public void eliminarProductoDeLista(Integer detalleOrdenId, String nickname);
}
