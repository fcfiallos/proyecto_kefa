package com.software.kefa.repository;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.DetalleOrden;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class DetalleOrdenRepositoryImpl implements IDetalleOrdenRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional (value = Transactional.TxType.MANDATORY)
    public void insertar(DetalleOrden detalleOrden) {
        this.entityManager.persist(detalleOrden);
    }

    @Override
    @Transactional (value = Transactional.TxType.MANDATORY)
    public void actualizar(DetalleOrden detalleOrden) {
        this.entityManager.merge(detalleOrden);
    }

    @Override
    @Transactional (value = Transactional.TxType.NOT_SUPPORTED)
    public DetalleOrden seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(DetalleOrden.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void eliminar(DetalleOrden detalleOrden) {
        this.entityManager.remove(detalleOrden);
    }

    @Override
    public DetalleOrden seleccionarPorIdCarrito(Integer idCarrito) {
        try {
            return this.entityManager.createQuery("SELECT d FROM DetalleOrden d WHERE d.carritoCompra.id = :idCarrito", DetalleOrden.class)
                    .setParameter("idCarrito", idCarrito).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public DetalleOrden guardarOActualizarDetalleOrden(DetalleOrden detalleOrden) {
        if (detalleOrden.getId() == null) {
            this.entityManager.persist(detalleOrden);
            return detalleOrden;
        } else {
            return this.entityManager.merge(detalleOrden);
        }
    }

}
