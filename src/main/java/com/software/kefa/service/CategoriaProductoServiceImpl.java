package com.software.kefa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.repository.ICategoriaProductoRepository;
import com.software.kefa.repository.modelo.CategoriaProducto;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

/**
 * This class implements the ICategoriaProductoService interface and provides
 * the implementation for the service methods related to the CategoriaProducto entity.
 */
@Service
public class CategoriaProductoServiceImpl implements ICategoriaProductoService{

    @Autowired
    private ICategoriaProductoRepository categoriaProductoRepository;

    /**
        * Guarda una instancia de CategoriaProducto en la base de datos.
        *
        * @param categoriaProducto La instancia de CategoriaProducto a guardar.
        */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(CategoriaProducto categoriaProducto) {
        this.categoriaProductoRepository.insertar(categoriaProducto);
    }

    /**
        * Actualiza una categoría de producto en la base de datos.
        *
        * @param categoriaProducto La categoría de producto a actualizar.
        */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(CategoriaProducto categoriaProducto) {
        this.categoriaProductoRepository.actualizar(categoriaProducto);
    }

    /**
        * Retrieves all the CategoriaProducto objects.
        *
        * @return a list of CategoriaProducto objects
        */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<CategoriaProducto> buscarTodo() {
        return this.categoriaProductoRepository.seleccionarTodo();
    }

    /**
        * Busca una categoría de producto por su ID.
        *
        * @param id el ID de la categoría de producto a buscar
        * @return la categoría de producto encontrada
        * @throws IllegalArgumentException si el ID es nulo
        */
    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public CategoriaProducto buscarPorId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser null");
        }
        return this.categoriaProductoRepository.seleccionarPorId(id);
    }

}
