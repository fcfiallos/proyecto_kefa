package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Cliente;
import com.software.kefa.repository.modelo.modelosdto.ClienteDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Repository
@Transactional
public class ClienteRepositoryImple implements IClienteRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = TxType.REQUIRED)
    public void insertar(Cliente cliente) {
        this.entityManager.persist(cliente);
    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void actualizar(Cliente cliente) {
        this.entityManager.merge(cliente);
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public Cliente seleccionar(String cedula) {
        TypedQuery<Cliente> query = this.entityManager.createQuery("SELECT c FROM Cliente c WHERE c.cedula= :cedula", Cliente.class);
        query.setParameter("cedula", cedula);
        return query.getSingleResult();
    }

    @Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<ClienteDTO> seleccionarTodo() {
        TypedQuery<ClienteDTO> myQuery = this.entityManager.createQuery("SELECT NEW com.software.kefa.repository.modelo.modelosdto.ClienteDTO (c.nombre, c.apellido, c.cedula, c.correoElectronico) FROM Cliente c",ClienteDTO.class);
        return myQuery.getResultList();
    }

}
