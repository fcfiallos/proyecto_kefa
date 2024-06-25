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

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(ListaDeseos listaDeseo) {
        this.listaDeseoRepository.insertar(listaDeseo);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(ListaDeseos listaDeseo) {
        this.listaDeseoRepository.actualizar(listaDeseo);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Set<Producto> buscarTodo(Integer id) {
        return this.listaDeseoRepository.seleccionarTodo(id);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public ListaDeseos buscarPorId(Integer id) {
        return this.listaDeseoRepository.seleccionarPorId(id);
    }

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
