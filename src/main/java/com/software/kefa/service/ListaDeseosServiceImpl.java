package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.IDetalleOrdenRepository;
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

    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;

    /**
     * Updates the given ListaDeseos object.
     *
     * @param listaDeseo The ListaDeseos object to be updated.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(ListaDeseos listaDeseo) {
        this.listaDeseoRepository.actualizar(listaDeseo);
    }

    /**
     * Retrieves all products from the ListaDeseos with the given id.
     *
     * @param id The id of the ListaDeseos.
     * @return A list of Producto objects.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<Producto> buscarTodo(Integer id) {
        return this.listaDeseoRepository.seleccionarTodo(id);
    }

    /**
     * Retrieves the ListaDeseos with the given id.
     *
     * @param id The id of the ListaDeseos.
     * @return The ListaDeseos object.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public ListaDeseos buscarPorId(Integer id) {
        return this.listaDeseoRepository.seleccionarPorId(id);
    }

    /**
     * Adds a Producto to the ListaDeseos with the given product id, nickname, and ListaDeseos object.
     *
     * @param productoId The id of the Producto to be added.
     * @param nickname   The nickname of the user.
     * @param listaDeseo The ListaDeseos object.
     * @return The updated ListaDeseos object.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public ListaDeseos agregarProductoALaLista(Integer productoId, String nickname, ListaDeseos listaDeseo) {
        Producto producto = productoRepository.seleccionarPorId(productoId);
        Usuario usuario = iUsuarioRepository.seleccionarPorNickname(nickname);
        listaDeseo = usuario.getListaDeseos();

        if (listaDeseo == null) {
            listaDeseo = new ListaDeseos();
            listaDeseo.setFechaSeleccionada(LocalDateTime.now());
            listaDeseo.setUsuario(usuario);
            listaDeseoRepository.insertar(listaDeseo);
        }
        DetalleOrden detalleOrden = new DetalleOrden();
        detalleOrden.setProducto(producto);
        this.detalleOrdenRepository.insertar(detalleOrden);
        return listaDeseo;
    }

    /**
     * Saves the given ListaDeseos object.
     *
     * @param listaDeseo The ListaDeseos object to be saved.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(ListaDeseos listaDeseo) {
        this.listaDeseoRepository.insertar(listaDeseo);
    }

    /**
     * Retrieves the list of DetalleOrden objects associated with the ListaDeseos with the given id.
     *
     * @param id The id of the ListaDeseos.
     * @return A list of DetalleOrden objects.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<DetalleOrden> buscarDetallePorIdLista(Integer id) {
        return this.listaDeseoRepository.seleccionarDetalleOrdenPorListaDeseoId(id);
    }

    /**
     * Removes a Producto from the ListaDeseos with the given DetalleOrden id and nickname.
     *
     * @param detalleOrdenId The id of the DetalleOrden to be removed.
     * @param nickname       The nickname of the user.
     */
    @Override
    public void eliminarProductoDeLista(Integer detalleOrdenId, String nickname) {
        this.listaDeseoRepository.eliminarProductoDeLista(detalleOrdenId, nickname);
    }

}
