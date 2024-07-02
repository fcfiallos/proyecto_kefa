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

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(ListaDeseos listaDeseo) {
        this.listaDeseoRepository.actualizar(listaDeseo);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<Producto> buscarTodo(Integer id) {
        return this.listaDeseoRepository.seleccionarTodo(id);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public ListaDeseos buscarPorId(Integer id) {
        return this.listaDeseoRepository.seleccionarPorId(id);
    }

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
        detalleOrden.setListaDeseos(listaDeseo);
        this.detalleOrdenRepository.insertar(detalleOrden);
        return listaDeseo;
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(ListaDeseos listaDeseo) {
        this.listaDeseoRepository.insertar(listaDeseo);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<DetalleOrden> buscarDetallePorIdLista(Integer id) {
        return this.listaDeseoRepository.seleccionarDetalleOrdenPorListaDeseoId(id);
    }

    @Override
    public void eliminarProductoDeLista(Integer detalleOrdenId, String nickname) {
        this.listaDeseoRepository.eliminarProductoDeLista(detalleOrdenId, nickname);
    }

}
