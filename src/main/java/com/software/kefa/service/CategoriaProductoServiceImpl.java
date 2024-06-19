package com.software.kefa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.ICategoriaProductoRepository;
import com.software.kefa.repository.modelo.CategoriaProducto;

import jakarta.transaction.Transactional;

@Service
public class CategoriaProductoServiceImpl implements ICategoriaProductoService{

    @Autowired
    private ICategoriaProductoRepository categoriaProductoRepository;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)   
    public List<CategoriaProducto> buscarTodo() {
        return this.categoriaProductoRepository.seleccionarTodo();
    }

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

}
