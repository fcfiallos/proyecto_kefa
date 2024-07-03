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

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(CarritoCompra carritoCompra) {
        this.carritoCompraRepository.actualizar(carritoCompra);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public CarritoCompra buscarPorId(Integer id) {
        return this.carritoCompraRepository.seleccionarPorId(id);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<Producto> buscarTodo(Integer idCarritoCompra) {
        return this.carritoCompraRepository.seleccionarTodo(idCarritoCompra);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public CarritoCompra agregarProductoAlCarrito(Integer productoId, String nickname,
            Integer cantidad, CarritoCompra carritoCompra) {
        Producto producto = this.productoRepository.seleccionarPorId(productoId);
        Usuario usuario = this.iUsuarioRepository.seleccionarPorNickname(nickname);
        carritoCompra = usuario.getCarritoCompra();
        if (carritoCompra == null) {
            carritoCompra = new CarritoCompra();
            carritoCompra.setFechaSeleccionada(LocalDateTime.now());
            carritoCompra.setUsuario(usuario);
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

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void eliminar(CarritoCompra carritoCompra) {
        carritoCompraRepository.eliminar(carritoCompra);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(CarritoCompra carritoCompra) {
        this.carritoCompraRepository.insertar(carritoCompra);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public CarritoCompra buscarPorNickname(String nickname) {
        return this.carritoCompraRepository.seleccionarPorUsuarioNickname(nickname);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<DetalleOrden> buscarDetallePorIdCarrito(Integer idCarritoCompra) {
        return this.carritoCompraRepository.seleccionarDetalleOrdenPorCarritoCompraId(idCarritoCompra);
    }

}
