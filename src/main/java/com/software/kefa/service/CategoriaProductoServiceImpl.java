package com.software.kefa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.ICategoriaProductoRepository;
import com.software.kefa.repository.modelo.CategoriaProducto;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class CategoriaProductoServiceImpl implements ICategoriaProductoService{

    @Autowired
    private ICategoriaProductoRepository categoriaProductoRepository;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(CategoriaProducto categoriaProducto) {
        this.categoriaProductoRepository.insertar(categoriaProducto);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(CategoriaProducto categoriaProducto) {
        this.categoriaProductoRepository.actualizar(categoriaProducto);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<CategoriaProducto> buscarTodo() {
        return this.categoriaProductoRepository.seleccionarTodo();
    }

    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public CategoriaProducto buscarPorId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser null");
        }
        return this.categoriaProductoRepository.seleccionarPorId(id);
    }

}
