package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.IListaDeseoRepository;
import com.software.kefa.repository.IProductoRepository;
import com.software.kefa.repository.modelo.ListaDeseos;
import com.software.kefa.repository.modelo.Producto;

import jakarta.transaction.Transactional;

@Service
public class ListaDeseosServiceImpl implements IListaDeseoService {

    @Autowired
    private IListaDeseoRepository listaDeseoRepository;

    @Autowired
    private IProductoRepository productoRepository;

    /**
     * Guarda una lista de deseos en el repositorio.
     *
     * @param listaDeseo la lista de deseos a guardar
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(ListaDeseos listaDeseo) {
        this.listaDeseoRepository.insertar(listaDeseo);
    }

    /**
     * Actualiza una lista de deseos en el repositorio.
     *
     * @param listaDeseo la lista de deseos a actualizar
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(ListaDeseos listaDeseo) {
        this.listaDeseoRepository.actualizar(listaDeseo);
    }

    /**
     * Busca todos los productos en una lista de deseos.
     *
     * @param id el ID de la lista de deseos
     * @return un conjunto de productos en la lista de deseos
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Set<Producto> buscarTodo(Integer id) {
        return this.listaDeseoRepository.seleccionarTodo(id);
    }

    /**
     * Busca una lista de deseos por su ID.
     *
     * @param id el ID de la lista de deseos a buscar
     * @return la lista de deseos encontrada
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public ListaDeseos buscarPorId(Integer id) {
        return this.listaDeseoRepository.seleccionarPorId(id);
    }

    /**
     * Agrega un producto a una lista de deseos.
     *
     * @param listaDeseoId el ID de la lista de deseos
     * @param productoId el ID del producto a agregar
     * @throws RuntimeException si la lista de deseos o el producto no se encuentran
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void agregarProductoALaLista(Integer listaDeseoId, Integer productoId) {
        ListaDeseos listaDeseos = listaDeseoRepository.seleccionarPorId(listaDeseoId);
        listaDeseos.setFechaSeleccionada(LocalDateTime.now());
        Producto producto = productoRepository.seleccionarPorId(productoId);
        if (listaDeseos != null && producto != null) {
            listaDeseos.getProductos().add(producto);
            listaDeseoRepository.actualizar(listaDeseos);
        } else {
            throw new RuntimeException("Lista de deseos o Producto no encontrado");
        }
    }

}
