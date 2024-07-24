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

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


/**
 * This class implements the {@link ICarritoCompraService} interface and provides
 * the implementation for managing the shopping cart.
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

    @Autowired
    private EntityManager entityManager;

    /**
     * Updates the shopping cart by removing a product with the given detail ID.
     *
     * @param carritoCompra The shopping cart object.
     * @param detalleId     The ID of the detail to be removed.
     * @return The updated shopping cart object.
     * @throws IllegalArgumentException If the shopping cart or the detail ID is null.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public CarritoCompra actualizar(CarritoCompra carritoCompra, Integer detalleId) {
        if (carritoCompra == null) {
            throw new IllegalArgumentException("No se pudo eliminar el producto seleccionado");
        }
        CarritoCompra actualizado = this.carritoCompraRepository.unir(carritoCompra);
        try {
            if (actualizado != null) {
                actualizado.getDetalleOrden().removeIf(detalle -> detalle.getId() == detalleId);
                actualizado.getDetalleOrden().forEach(System.out::println);
            } 
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo eliminar el carrito de compra");
        }
        return actualizado;
    }

    /**
     * Retrieves the shopping cart with the given ID.
     *
     * @param id The ID of the shopping cart.
     * @return The shopping cart object.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public CarritoCompra buscarPorId(Integer id) {
        return this.carritoCompraRepository.seleccionarPorId(id);
    }

    /**
     * Retrieves all products in the shopping cart with the given ID.
     *
     * @param idCarritoCompra The ID of the shopping cart.
     * @return A list of products in the shopping cart.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<Producto> buscarTodo(Integer idCarritoCompra) {
        return this.carritoCompraRepository.seleccionarTodo(idCarritoCompra);
    }

    /**
     * Adds a product to the shopping cart for a given user.
     *
     * @param productoId    The ID of the product to be added.
     * @param nickname      The nickname of the user.
     * @param cantidad      The quantity of the product to be added.
     * @param carritoCompra The shopping cart object.
     * @return The updated shopping cart object.
     * @throws RuntimeException If the product is not found.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public CarritoCompra agregarProductoAlCarrito(Integer productoId, String nickname,
            Integer cantidad, CarritoCompra carritoCompra) {
        Producto producto = this.productoRepository.seleccionarPorId(productoId);
        Usuario usuario = this.iUsuarioRepository.seleccionarPorNickname(nickname);
        
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        Usuario usuarioActualizado = entityManager.merge(usuario);
        carritoCompra = usuarioActualizado.getCarritoCompra();

        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado");
        }
        
        if (carritoCompra == null) {
            carritoCompra = new CarritoCompra();
            carritoCompra.setFechaSeleccionada(LocalDateTime.now());
            carritoCompra.setUsuario(usuarioActualizado);
            carritoCompra.setCantidad(cantidad);
            carritoCompraRepository.insertar(carritoCompra);
        }

        DetalleOrden detalleOrden = new DetalleOrden();
        detalleOrden.setProducto(producto);
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setCarritoCompra(carritoCompra);
        detalleOrdenRepository.insertar(detalleOrden);
        return carritoCompra;
    }


    /**
     * Saves the shopping cart.
     *
     * @param carritoCompra The shopping cart object to be saved.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(CarritoCompra carritoCompra) {
        this.carritoCompraRepository.insertar(carritoCompra);
    }

}
