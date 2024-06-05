package com.software.kefa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.IProductoRepository;
import com.software.kefa.repository.IProveedorRepository;
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.repository.modelo.Proveedor;
import com.software.kefa.repository.modelo.modelosdto.ProductoDTO;
import com.software.kefa.service.modelosto.ProductoTO;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
@Service
public class ProductoServiceImpl implements IProductoService{

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private IProveedorRepository proveedorRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Transactional(value = TxType.REQUIRES_NEW)
    @Override
    public List<ProductoDTO> buscarTodo() {
        // TODO Auto-generated method stub
        return this.productoRepository.seleccionarTodo();
    }

    @Transactional(value = TxType.REQUIRES_NEW)
    @Override
    public void guardar(ProductoTO producto) {
        // TODO Auto-generated method stub
        Producto pro = new Producto();
        Proveedor prov = this.proveedorRepository.seleccionarPorNombre(producto.getNombreProveedor());
        pro.setCantidad(producto.getCantidad());
        pro.setCodigo(producto.getCodigo());
        pro.setDescripcion(producto.getDescripcion());
        pro.setEstado(producto.getEstado());
        pro.setImagen(producto.getImagen());
        pro.setNombre(producto.getNombre());
        pro.setPrecio(producto.getPrecio());
        pro.setProveedor(prov);

        this.productoRepository.insertar(pro);
    }

    @Transactional(value = TxType.REQUIRES_NEW)
    @Override
    public void actualizar(Producto producto) {
        // TODO Auto-generated method stub
        this.productoRepository.actualizar(producto);
    }

}
