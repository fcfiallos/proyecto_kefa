package com.software.kefa.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.excepcion.MensajeExisteExcepcion;
import com.software.kefa.repository.INotificacionRepository;
import com.software.kefa.repository.IProductoRepository;
import com.software.kefa.repository.IPromocionRepository;
import com.software.kefa.repository.IUsuarioRepository;
import com.software.kefa.repository.modelo.Notificacion;
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.repository.modelo.Promocion;
import com.software.kefa.repository.modelo.Usuario;
import com.software.kefa.service.modelosto.PromocionTO;

import jakarta.transaction.Transactional;

/**
 * This class implements the IPromocionService interface and provides the implementation for managing promotions.
 * It interacts with the IPromocionRepository, IProductoRepository, INotificacionRepository, and IUsuarioRepository
 * to perform CRUD operations on promotions and related entities.
 */
@Service
public class PromocionServiceImpl implements IPromocionService {
    @Autowired
    private IPromocionRepository promocionRepository;
    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private INotificacionRepository notificacionRepository;
    @Autowired
    private IUsuarioRepository usuarioRepository;

    /**
     * Saves a new promotion with the given details.
     *
     * @param promocion The promotion details to be saved.
     * @param nickname The nickname of the user creating the promotion.
     * @throws MensajeExisteExcepcion If the user does not exist or the product code is invalid.
     */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(PromocionTO promocion, String nickname) {
        Promocion prom = new Promocion();
        Producto producto = this.productoRepository.seleccionarPorCodigo(promocion.getCodigo());
        Usuario usuario = this.usuarioRepository.seleccionarPorNickname(nickname);

        if (usuario == null) {
            throw new MensajeExisteExcepcion("El usuario no existe");
        }

        if (producto != null) {

            prom.setFechaInicio(promocion.getFechaInicio());
            prom.setEstado("Vigente");
            prom.setFechaFin(promocion.getFechaFin());
            BigDecimal descuento = new BigDecimal(promocion.getDescuento());
            prom.setDescuento(descuento);
            prom.setTipoPromocion(promocion.getTipo());
            this.promocionRepository.insertar(prom);

            Notificacion notificacion = new Notificacion();
            notificacion.setFecha(LocalDateTime.now());
            notificacion.setTipo("Promoción");
            notificacion.setMensaje("Se ha creado una nueva promoción para el producto " + producto.getNombre());
            notificacion.setUsuario(usuario);
            this.notificacionRepository.insertar(notificacion);

        } else {
            throw new MensajeExisteExcepcion("El código del producto no existe");
        }

    }

    /**
        * Actualiza una promoción en el sistema.
        *
        * @param promocion La promoción a actualizar.
        */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(Promocion promocion) {
        this.promocionRepository.actualizar(promocion);
    }

    /**
        * Busca una promoción por su identificador.
        *
        * @param id el identificador de la promoción a buscar
        * @return la promoción encontrada, o null si no se encuentra ninguna promoción con el identificador dado
        */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Promocion buscarPorId(Integer id) {
        return this.promocionRepository.seleccionarPorId(id);
    }

    /**
        * Busca una promoción por fechas.
        *
        * @param fechaInicio La fecha de inicio de la promoción.
        * @param fechaFin La fecha de fin de la promoción.
        * @return La promoción encontrada, o null si no se encuentra ninguna promoción en el rango de fechas dado.
        */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Promocion buscarPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return this.promocionRepository.seleccionarPorFechas(fechaInicio, fechaFin);
    }

    /**
        * Retrieves all the promotions.
        *
        * @return a list of Promocion objects representing all the promotions.
        */
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<Promocion> buscarTodo() {
        return this.promocionRepository.seleccionarTodo();
    }

}
