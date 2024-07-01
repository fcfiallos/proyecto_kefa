package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.CarritoCompra;
import com.software.kefa.repository.modelo.DetalleOrden;
import com.software.kefa.repository.modelo.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CarritoCompraRepositoryImpl implements ICarritoCompraRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(CarritoCompra carritoCompra) {
        this.entityManager.persist(carritoCompra);
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(CarritoCompra carritoCompra) {
        this.entityManager.merge(carritoCompra);
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public CarritoCompra seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(CarritoCompra.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public List<Producto> seleccionarTodo(Integer idCarritoCompra) {
        try {
            String jpql = "SELECT p FROM Producto p JOIN p.detalleOrden do JOIN do.carritoCompra cc WHERE cc.id = :idCarritoCompra";
        return this.entityManager.createQuery(jpql, Producto.class)
                .setParameter("idCarritoCompra", idCarritoCompra).getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

	@Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
	public CarritoCompra seleccionarPorUsuarioNickname(String nickname) {
        try {
            String jpql = "SELECT cc FROM CarritoCompra cc JOIN cc.usuario u WHERE u.nickname = :nickname";
            return this.entityManager.createQuery(jpql, CarritoCompra.class)
                    .setParameter("nickname", nickname).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
		
	}

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public List<DetalleOrden> seleccionarDetalleOrdenPorCarritoCompraId(Integer id) {
        try {
            return this.entityManager.createQuery("SELECT do FROM DetalleOrden do JOIN do.carritoCompra cc WHERE cc.id = :id",
                    DetalleOrden.class).setParameter("id", id).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

	@Override
    @Transactional(value = Transactional.TxType.MANDATORY)
	public void eliminarProductoDelCarrito(Integer detalleId, String nickname) {
		this.entityManager.createQuery(
                "DELETE FROM DetalleOrden do WHERE do.id = :detalleId AND do.carritoCompra.usuario.nickname = :nickname")
                .setParameter("detalleId", detalleId).setParameter("nickname", nickname).executeUpdate();
	}

}

