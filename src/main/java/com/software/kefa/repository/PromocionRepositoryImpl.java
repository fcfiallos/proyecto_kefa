package com.software.kefa.repository;

import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

import com.software.kefa.repository.modelo.Promocion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class PromocionRepositoryImpl implements IPromocionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void insertar(Promocion promocion) {
        this.entityManager.persist(promocion);
    }

    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void actualizar(Promocion promocion) {
        this.entityManager.merge(promocion);
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Promocion seleccionarPorId(Integer id) {
        try {
            return this.entityManager.find(Promocion.class, id);
        } catch (NoResultException e) {
            return null;
        }
    }

	@Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
	public Promocion seleccionarPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		try {
            return this.entityManager.createQuery("SELECT p FROM Promocion p WHERE p.fechaInicio <= :fechaInicio AND p.fechaFin >= :fechaFin", Promocion.class)
                    .setParameter("fechaInicio", fechaInicio)
                    .setParameter("fechaFin", fechaFin)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
	}

}
