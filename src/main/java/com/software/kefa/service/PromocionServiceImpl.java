package com.software.kefa.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.kefa.excepcion.MensajeExisteExcepcion;
import com.software.kefa.repository.IProductoRepository;
import com.software.kefa.repository.IPromocionRepository;
import com.software.kefa.repository.modelo.Producto;
import com.software.kefa.repository.modelo.Promocion;
import com.software.kefa.service.modelosto.PromocionTO;

import jakarta.transaction.Transactional;

@Service
public class PromocionServiceImpl implements IPromocionService {
    @Autowired
    private IPromocionRepository promocionRepository;
    @Autowired
    private IProductoRepository productoRepository;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void guardar(PromocionTO promocion) {
        Promocion prom = new Promocion();
        Producto producto = this.productoRepository.seleccionarPorCodigo(promocion.getCodigo());
        if (producto != null) {
            
            prom.setFechaInicio(promocion.getFechaInicio());
            prom.setEstado("Vigente");
            prom.setFechaFin(promocion.getFechaFin());
            BigDecimal descuento = new BigDecimal(promocion.getDescuento());
            prom.setDescuento(descuento);
            prom.setTipoPromocion(promocion.getTipo());
            this.promocionRepository.insertar(prom);
        } else {
            throw new MensajeExisteExcepcion("El código del producto no existe");
        }

    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void actualizar(Promocion promocion) {
        this.promocionRepository.actualizar(promocion);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Promocion buscarPorId(Integer id) {
        return this.promocionRepository.seleccionarPorId(id);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Promocion buscarPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return this.promocionRepository.seleccionarPorFechas(fechaInicio, fechaFin);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<Promocion> buscarTodo() {
        return this.promocionRepository.seleccionarTodo();
    }

}
