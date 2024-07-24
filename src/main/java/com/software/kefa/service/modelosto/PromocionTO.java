package com.software.kefa.service.modelosto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * The PromocionTO class represents a promotional offer.
 * It contains information about the type of promotion, discount, start and end dates, and a code.
 */
public class PromocionTO {
    private String tipo;
    private String descuento;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFin;
    
    private String codigo;

    /**
     * Default constructor for the PromocionTO class.
     */
    public PromocionTO() {
    }

    /**
     * Constructor for the PromocionTO class with parameters.
     * 
     * @param tipo The type of promotion.
     * @param descuento The discount associated with the promotion.
     * @param fechaInicio The start date of the promotion.
     * @param fechaFin The end date of the promotion.
     * @param codigo The code associated with the promotion.
     */
    public PromocionTO(String tipo, String descuento, LocalDate fechaInicio, LocalDate fechaFin,
            String codigo) {
        this.tipo = tipo;
        this.descuento = descuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.codigo = codigo;
    }

    /**
     * Get the type of promotion.
     * 
     * @return The type of promotion.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Set the type of promotion.
     * 
     * @param tipo The type of promotion.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Get the discount associated with the promotion.
     * 
     * @return The discount associated with the promotion.
     */
    public String getDescuento() {
        return descuento;
    }

    /**
     * Set the discount associated with the promotion.
     * 
     * @param descuento The discount associated with the promotion.
     */
    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    /**
     * Get the start date of the promotion.
     * 
     * @return The start date of the promotion.
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Set the start date of the promotion.
     * 
     * @param fechaInicio The start date of the promotion.
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Get the end date of the promotion.
     * 
     * @return The end date of the promotion.
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Set the end date of the promotion.
     * 
     * @param fechaFin The end date of the promotion.
     */
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Get the code associated with the promotion.
     * 
     * @return The code associated with the promotion.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Set the code associated with the promotion.
     * 
     * @param codigo The code associated with the promotion.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
