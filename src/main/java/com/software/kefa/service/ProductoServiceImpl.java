package com.software.kefa.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.excepcion.MensajeExisteExcepcion;
import com.software.kefa.repository.ICategoriaProductoRepository;
import com.software.kefa.repository.IProductoRepository;
import com.software.kefa.repository.IProveedorRepository;
import com.software.kefa.repository.modelo.CategoriaProducto;
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.repository.modelo.Proveedor;
import com.software.kefa.service.modelosto.ProductoTO;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

/**
 * This class implements the {@link IProductoService} interface and provides the
 * implementation
 * for the service methods related to products.
 */
@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private IProveedorRepository proveedorRepository;

    @Autowired
    private ICategoriaProductoRepository categoriaProductoRepository;

    // @Autowired
    // private IUsuarioRepository usuarioRepository;

    @Transactional(value = TxType.REQUIRES_NEW)
    @Override
    public List<Producto> buscarPorCategoriaId(Integer categoriaID) {
        return this.productoRepository.seleccionarPorCategoriaId(categoriaID);
    }

    /**
     * Saves a new product.
     *
     * @param producto The product to be saved.
     */
    @Transactional(value = TxType.REQUIRES_NEW)
    @Override
    public void guardar(ProductoTO producto) {
        Producto pro = new Producto();
        Proveedor prov = new Proveedor();
        CategoriaProducto categoria = this.categoriaProductoRepository.seleccionarPorId(producto.getCategoriaId());

        if (this.existeProductoCodigo(producto.getCodigo())
                || this.existeProveedorNombre(producto.getNombreProveedor())) {
            throw new MensajeExisteExcepcion("El proveedor o el código del producto ya existe");
        }

        if (categoria == null) {
            throw new RuntimeException("La categoría especificada no existe.");
        }

        pro.setCantidad(producto.getCantidad());
        pro.setCodigo(producto.getCodigo());
        pro.setDescripcion(producto.getDescripcion());
        pro.setEstado("Disponible"); // en la entidad orden toca manipular cuando este agotado envia ese cambio de estado
        pro.setNombre(producto.getNombre());
        pro.setImagen(producto.getImagen());
        BigDecimal precio = new BigDecimal(producto.getPrecio());
        pro.setPrecio(precio);

        prov.setNombre(producto.getNombreProveedor());
        prov.setFechaRegistro(LocalDateTime.now());
        prov.setPais(producto.getPais());
        prov.setTipo(producto.getTipo());

        pro.setProveedor(prov);
        pro.setCategoriaProducto(categoria);

        this.productoRepository.insertar(pro);
        this.proveedorRepository.insertar(prov);
    }

    /**
        * Actualiza un producto en la base de datos.
        *
        * @param producto El producto a actualizar.
        */
    @Transactional(value = TxType.REQUIRES_NEW)
    @Override
    public void actualizar(Producto producto) {
        this.productoRepository.actualizar(producto);
    }

    /**
        * Checks if a product with the given code exists.
        *
        * @param codigo the code of the product to check
        * @return true if a product with the given code exists, false otherwise
        */
    @Override
    @Transactional (value = TxType.REQUIRES_NEW)
    public boolean existeProductoCodigo(String codigo) {
        Producto pro = this.productoRepository.seleccionarPorCodigo(codigo);
        return pro != null;
    }

    /**
        * Checks if a provider with the given name exists.
        *
        * @param nombre the name of the provider to check
        * @return true if a provider with the given name exists, false otherwise
        */
    @Override
    @Transactional (value = TxType.REQUIRES_NEW)
    public boolean existeProveedorNombre(String nombre) {
        Proveedor prov = this.proveedorRepository.seleccionarPorNombre(nombre);
        return prov != null;
    }

    @Override
    @Transactional (value = TxType.REQUIRES_NEW)
    public Producto buscarPorCodigo(String codigo) {
        return this.productoRepository.seleccionarPorCodigo(codigo);
    }

}