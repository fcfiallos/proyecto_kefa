
package com.software.kefa.service.modelosto;

/**
 * The `MetodoPagoTO` class represents a payment method transfer object.
 * It contains information about a payment method, such as the card number,
 * expiration date, cardholder name, and CVV.
 */
public class MetodoPagoTO {
    private String numeroTarjeta;
    private String fechaVencimiento;
    private String nombreTarjeta;
    private String cvv;

    /**
     * Default constructor for the `MetodoPagoTO` class.
     */
    public MetodoPagoTO() {
    }

    /**
     * Constructor for the `MetodoPagoTO` class with parameters.
     *
     * @param numeroTarjeta     The card number.
     * @param fechaVencimiento  The expiration date of the card.
     * @param nombreTarjeta     The name of the cardholder.
     * @param cvv               The CVV (Card Verification Value) of the card.
     */
    public MetodoPagoTO(String numeroTarjeta, String fechaVencimiento, String nombreTarjeta, String cvv) {
        this.numeroTarjeta = numeroTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.nombreTarjeta = nombreTarjeta;
        this.cvv = cvv;
    }

    /**
     * Get the card number.
     *
     * @return The card number.
     */
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * Set the card number.
     *
     * @param numeroTarjeta The card number to set.
     */
    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    /**
     * Get the expiration date of the card.
     *
     * @return The expiration date of the card.
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Set the expiration date of the card.
     *
     * @param fechaVencimiento The expiration date to set.
     */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Get the name of the cardholder.
     *
     * @return The name of the cardholder.
     */
    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    /**
     * Set the name of the cardholder.
     *
     * @param nombreTarjeta The name of the cardholder to set.
     */
    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }

    /**
     * Get the CVV (Card Verification Value) of the card.
     *
     * @return The CVV of the card.
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * Set the CVV (Card Verification Value) of the card.
     *
     * @param cvv The CVV to set.
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
