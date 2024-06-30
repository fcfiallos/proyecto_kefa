package com.software.kefa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.IListaDeseoRepository;
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.ListaDeseos;
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.repository.modelo.Usuario;

import jakarta.transaction.Transactional;

@Service
public class ListaDeseosServiceImpl implements IListaDeseoService {

    @Autowired
    private IListaDeseoRepository listaDeseoRepository;

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void agregarProducto(Integer listaDeseosId, Producto producto, String nickname) {
        ListaDeseos listaDeseos = listaDeseoRepository.seleccionarPorId(listaDeseosId);
        Usuario usuario = iUsuarioRepository.seleccionarPorNickname(nickname);
        if (listaDeseos == null) {
            listaDeseos = new ListaDeseos();
            listaDeseos.setId(listaDeseosId);
            listaDeseos.setFechaSeleccionada(LocalDateTime.now());
            listaDeseos.setCantidad(0);
            listaDeseos.setProductos(new ArrayList<>());
            listaDeseos.setUsuario(usuario);
            listaDeseoRepository.insertar(listaDeseos);
        }
        listaDeseos.agregarProducto(producto);
        listaDeseoRepository.actualizar(listaDeseos);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void eliminarProducto(Integer listaDeseosId, Producto producto) {
        ListaDeseos listaDeseos = listaDeseoRepository.seleccionarPorId(listaDeseosId);
        if (listaDeseos != null) {
            listaDeseos.eliminarProducto(producto);
            listaDeseoRepository.actualizar(listaDeseos);
        } else {
            throw new RuntimeException("La lista de deseos no existe.");
        }
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public ListaDeseos obtenerListaDeseosPorId(Integer id) {
        return this.listaDeseoRepository.seleccionarPorId(id);
    }

}
