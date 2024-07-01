package com.software.kefa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.DetalleOrden;
import com.software.kefa.repository.modelo.ListaDeseos;
import com.software.kefa.repository.modelo.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ListaDeseoRepositoryImpl implements IListaDeseoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(ListaDeseos listaDeseo) {
        this.entityManager.persist(listaDeseo);
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(ListaDeseos listaDeseo) {
        this.entityManager.merge(listaDeseo);
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public List<Producto> seleccionarTodo(Integer listaDeseosId) {
        try {
            return this.entityManager.createQuery(
                    "SELECT p FROM Producto p JOIN DetalleOrden do ON p.id = do.producto.id WHERE do.listaDeseos.id = :listaDeseosId",
                    Producto.class)
                    .setParameter("listaDeseosId", listaDeseosId).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public ListaDeseos seleccionarPorId(Integer id) {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("El ID proporcionado es inválido: " + id);
            }
            return this.entityManager.find(ListaDeseos.class, id);
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public ListaDeseos seleccionarPorUsuarioNickname(String nickname) {
        try {
            return this.entityManager.createQuery("SELECT ld FROM ListaDeseos ld JOIN ld.usuario u WHERE u.nickname = :nickname",
                    ListaDeseos.class).setParameter("nickname", nickname).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void eliminarProductoDeLista(Integer detalleId, String nickname) {
        this.entityManager.createQuery(
                "DELETE FROM DetalleOrden do WHERE do.id = :detalleId AND do.listaDeseos.usuario.nickname = :nickname")
                .setParameter("detalleId", detalleId).setParameter("nickname", nickname).executeUpdate();
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public List<DetalleOrden> seleccionarDetalleOrdenPorListaDeseoId(Integer id) {
        try {
            return this.entityManager.createQuery("SELECT do FROM DetalleOrden do JOIN do.listaDeseos ld WHERE ld.id = :id",
                    DetalleOrden.class).setParameter("id", id).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
