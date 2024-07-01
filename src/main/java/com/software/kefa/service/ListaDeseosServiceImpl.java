package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.IListaDeseoRepository;
import com.software.kefa.repository.IProductoRepository;
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.DetalleOrden;
import com.software.kefa.repository.modelo.ListaDeseos;
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.repository.modelo.Usuario;

import jakarta.transaction.Transactional;

@Service
public class ListaDeseosServiceImpl implements IListaDeseoService {

    @Autowired
    private IListaDeseoRepository listaDeseoRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private IUsuarioRepository iUsuarioRepository;
    
    /**
        * Saves a ListaDeseos object with the given parameters.
        * 
        * @param listaDeseo The ListaDeseos object to be saved.
        * @param nickname The nickname of the user.
        * @param productoId The ID of the product.
        */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(ListaDeseos listaDeseo, String nickname, Integer productoId) {
        listaDeseo.setFechaSeleccionada(LocalDateTime.now());

        Usuario usuario = iUsuarioRepository.seleccionarPorNickname(nickname);
        listaDeseo.setUsuario(usuario);

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
    public List<Producto> buscarTodo(Integer id) {
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
        Producto producto = productoRepository.seleccionarPorId(productoId);
        if (listaDeseos != null && producto != null) {
            DetalleOrden detalle = new DetalleOrden();
            detalle.setListaDeseos(listaDeseos);
            detalle.setProducto(producto);

            listaDeseos.getDetallesOrdenes().add(detalle);
            listaDeseoRepository.actualizar(listaDeseos);
        } else {
            throw new RuntimeException("Lista de deseos o Producto no encontrado");
        }
    }

}
