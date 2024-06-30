package com.software.kefa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.ICarritoCompraRepository;
import com.software.kefa.repository.IDetalleOrdenRepository;
import com.software.kefa.repository.IOrdenRepository;
import com.software.kefa.repository.IProductoRepository;
import com.software.kefa.repository.IPromocionRepository;
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.Orden;
import com.software.kefa.repository.modelo.modelosdto.OrdenDTO;

import jakarta.transaction.Transactional;

@Service
public class OrdenServiceImpl implements IOrdenService{
    @Autowired
    private IOrdenRepository ordenRepository;
    @Autowired
    private IPromocionRepository promocionRepository;
    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;
    @Autowired
    private IUsuarioRepository  usuarioRepository;
    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private ICarritoCompraRepository carritoCompraRepository;

    @Override
    @Transactional (value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(Orden orden) {
        this.ordenRepository.insertar(orden);
    }

    @Override
    @Transactional (value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(Orden orden) {
        this.ordenRepository.actualizar(orden);
    }

    @Override
    @Transactional (value = Transactional.TxType.REQUIRES_NEW)
    public Orden buscarPorId(Integer id) {
        return this.ordenRepository.seleccionarPorId(id);
    }

    @Override
    @Transactional (value = Transactional.TxType.REQUIRES_NEW)
    public Orden buscarTodo() {
        return this.ordenRepository.seleccionarTodo();
    }

    @Override
    @Transactional (value = Transactional.TxType.REQUIRES_NEW)
    public Orden buscarPorCodigo(String codigo) {
        return this.ordenRepository.seleccionarPorCodigo(codigo);
    }

    @Override
    @Transactional (value = Transactional.TxType.REQUIRES_NEW)
    public List<OrdenDTO> buscarTodos() {
        return this.ordenRepository.seleccionarTodos();
    }

    @Override
    @Transactional (value = Transactional.TxType.REQUIRES_NEW)
    public void crearOrdenDeCompra() {
        
    }

    


}
