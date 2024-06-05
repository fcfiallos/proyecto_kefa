package com.software.kefa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.ICategoriaProductoRepository;
import com.software.kefa.repository.modelo.CategoriaProducto;
import com.software.kefa.repository.modelo.modelosdto.CategoriaProductoDTO;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class CategoriaProductoServiceImpl implements ICategoriaProductoService{

    @Autowired
    private ICategoriaProductoRepository categoriaProductoRepository;

    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public List<CategoriaProductoDTO> buscarTodo() {
        // TODO Auto-generated method stub
        return this.categoriaProductoRepository.seleccionarTodo();
    }

    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public void guardar(CategoriaProducto categoriaProducto) {
        // TODO Auto-generated method stub
        CategoriaProducto catep = new CategoriaProducto();
        catep.setDescripcion(categoriaProducto.getDescripcion());
        catep.setTipo(categoriaProducto.getTipo());
        catep.setProductos(null);

        this.categoriaProductoRepository.insertar(catep);
    }

    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public void actualizar(CategoriaProducto categoriaProducto) {
        // TODO Auto-generated method stub
        this.categoriaProductoRepository.actualizar(categoriaProducto);
    }


}
