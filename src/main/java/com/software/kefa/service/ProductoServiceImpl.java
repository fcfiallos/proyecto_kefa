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
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.CategoriaProducto;
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.repository.modelo.Proveedor;
import com.software.kefa.repository.modelo.Usuario;
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

    @Autowired
    private IUsuarioRepository usuarioRepository;

    /**
     * Busca productos por ID de categoría.
     *
     * @param categoriaID el ID de la categoría
     * @return una lista de productos que pertenecen a la categoría especificada
     */
    @Transactional(value = TxType.REQUIRES_NEW)
    @Override
    public List<Producto> buscarPorCategoriaId(Integer categoriaID) {
        return this.productoRepository.seleccionarPorCategoriaId(categoriaID);
    }

 
    /**
     * Saves a new product with the given details.
     *
     * @param producto   The product details to be saved.
     * @param nickname   The nickname of the user saving the product.
     * @throws MensajeExisteExcepcion if the product code already exists.
     * @throws RuntimeException if the specified category does not exist.
     */
    @Transactional(value = TxType.REQUIRES_NEW)
    @Override
    public void guardar(ProductoTO producto, String nickname) {
        Producto pro = new Producto();
        Proveedor prov = new Proveedor();
        Usuario usuario = this.usuarioRepository.seleccionarPorNickname(nickname);
        CategoriaProducto categoria = this.categoriaProductoRepository.seleccionarPorId(producto.getCategoriaId());

        if (this.existeProductoCodigo(producto.getCodigo())
                /*|| this.existeProveedorNombre(producto.getNombreProveedor())*/) {
            throw new MensajeExisteExcepcion("El código del producto ya existe, por favor ingrese otro.");
        }

        if (categoria == null) {
            throw new RuntimeException("La categoría especificada no existe, por favor ingrese una válida.");
        }

        pro.setCantidad(producto.getCantidad());
        pro.setCodigo(producto.getCodigo());
        pro.setDescripcion(producto.getDescripcion());
        pro.setEstado("Disponible"); 
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
        pro.setUsuario(usuario);

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
    @Transactional(value = TxType.REQUIRES_NEW)
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
    @Transactional(value = TxType.REQUIRES_NEW)
    public boolean existeProveedorNombre(String nombre) {
        Proveedor prov = this.proveedorRepository.seleccionarPorNombre(nombre);
        return prov != null;
    }

    /**
     * Busca un producto por su código.
     *
     * @param codigo el código del producto a buscar
     * @return el producto encontrado, o null si no se encuentra ninguno con el
     *         código especificado
     */
    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public Producto buscarPorCodigo(String codigo) {
        return this.productoRepository.seleccionarPorCodigo(codigo);
    }

    /**
     * Busca un producto por su ID.
     *
     * @param id el ID del producto a buscar
     * @return el producto encontrado, o null si no se encuentra ninguno con el ID
     *         especificado
     */
    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public Producto buscarPorId(Integer id) {
        return this.productoRepository.seleccionarPorId(id);
    }

    @Override
    public List<Producto> buscarTodo() {
        return this.productoRepository.seleccionarTodo();
    }

   
    /**
        * Actualiza el stock de un producto dado su ID y la cantidad a agregar o restar.
        * Si el producto no existe, se lanza una excepción.
        * Si no hay suficiente stock para el producto, se lanza una excepción.
        *
        * @param productoId el ID del producto a actualizar el stock
        * @param cantidad la cantidad a agregar o restar al stock actual
        * @throws RuntimeException si el producto no se encuentra o no hay suficiente stock
        */
    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public void actualizarStock(Integer productoId, Integer cantidad) {
        Producto producto = this.productoRepository.seleccionarPorId(productoId);
        if (producto == null) {
            throw new RuntimeException("Producto no encontrado con ID: " + productoId);
        }
        int nuevoStock = producto.getCantidad() + cantidad;
        if (nuevoStock < 0) {
            throw new RuntimeException("No hay suficiente stock para el producto: " + producto.getNombre());
        }
        producto.setCantidad(nuevoStock);
        this.productoRepository.actualizar(producto);
    }

}