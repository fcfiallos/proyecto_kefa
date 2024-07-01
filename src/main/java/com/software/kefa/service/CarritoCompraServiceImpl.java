package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.ICarritoCompraRepository;
import com.software.kefa.repository.IDetalleOrdenRepository;
import com.software.kefa.repository.IProductoRepository;
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.DetalleOrden;
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.repository.modelo.Usuario;

import jakarta.transaction.Transactional;

/**
 * This class implements the {@link ICarritoCompraService} interface and
 * provides the implementation
 * for managing the shopping cart operations.
 */
@Service
public class CarritoCompraServiceImpl implements ICarritoCompraService {

    @Autowired
    private ICarritoCompraRepository carritoCompraRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;

    /**
     * Saves a CarritoCompra object to the database.
     * 
     * @param carritoCompra The CarritoCompra object to be saved.
     * @param nickname      The nickname of the user associated with the
     *                      CarritoCompra.
     * @param productoId    The ID of the product to be added to the CarritoCompra.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(CarritoCompra carritoCompra, String nickname, Integer productoId, Integer cantidad) {
        carritoCompra.setFechaSeleccionada(LocalDateTime.now());
        carritoCompra.setCantidad(cantidad);

        Usuario usuario = iUsuarioRepository.seleccionarPorNickname(nickname);
        carritoCompra.setUsuario(usuario);

        /*Producto producto = productoRepository.seleccionarPorId(productoId);
        if (producto == null) {
            throw new MensajeExisteExcepcion("Producto no encontrado");

        }

        // Verificar si la lista de productos es null y, de ser así, inicializarla
        if (carritoCompra.getProductos() == null) {
            carritoCompra.setProductos(new ArrayList<>());
        }

        carritoCompra.getProductos().add(producto);*/

        this.carritoCompraRepository.insertar(carritoCompra);
    }

    /**
     * Updates a shopping cart in the repository.
     *
     * @param carritoCompra the shopping cart to update
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(CarritoCompra carritoCompra) {
        this.carritoCompraRepository.actualizar(carritoCompra);
    }

    /**
     * Retrieves a shopping cart by its ID.
     *
     * @param id the ID of the shopping cart
     * @return the shopping cart with the specified ID
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public CarritoCompra buscarPorId(Integer id) {
        return this.carritoCompraRepository.seleccionarPorId(id);
    }

    /**
     * Retrieves all products in a shopping cart.
     *
     * @return a set of products in the shopping cart
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<Producto> buscarTodo(Integer idCarritoCompra) {
        return this.carritoCompraRepository.seleccionarTodo(idCarritoCompra);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void agregarProductoAlCarrito(Integer carritoCompraId, Integer productoId, String nickname,
            Integer cantidad) {
        Usuario usuario = this.iUsuarioRepository.seleccionarPorNickname(nickname);
        CarritoCompra carritoCompra = carritoCompraRepository.seleccionarPorId(carritoCompraId);

        Producto producto = productoRepository.seleccionarPorId(productoId);
        if (carritoCompra != null && producto != null) {
            DetalleOrden detalle = new DetalleOrden();
            detalle.setCarritoCompra(carritoCompra);
            detalle.setProducto(producto);
            detalle.setCantidad(cantidad);

            carritoCompra.getDetalleOrden().add(detalle);
            carritoCompra.setUsuario(usuario);
            this.detalleOrdenRepository.insertar(detalle);
            carritoCompraRepository.actualizar(carritoCompra);
        } else {
            throw new RuntimeException("Carrito de Compras o Producto no encontrado");
        }
    }

    /**
     * Deletes a product from a shopping cart.
     *
     * @param carritoCompraId the ID of the shopping cart
     * @param productoId      the ID of the product to delete
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void eliminarProductoDelCarrito(Integer carritoCompraId, Integer productoId) {
        CarritoCompra carritoCompra = carritoCompraRepository.seleccionarPorId(carritoCompraId);
        if (carritoCompra == null) {
            throw new RuntimeException("Carrito de Compras no encontrado");
        }

        Producto producto = productoRepository.seleccionarPorId(productoId);
        if (producto == null) {
            throw new RuntimeException("Producto no encontrado");
        }

        boolean productoEliminado = carritoCompra.getDetalleOrden().remove(producto);
        if (!productoEliminado) {
            throw new RuntimeException("El producto no se encontraba en el carrito de compras");
        }

        carritoCompraRepository.actualizar(carritoCompra);
    }

}
