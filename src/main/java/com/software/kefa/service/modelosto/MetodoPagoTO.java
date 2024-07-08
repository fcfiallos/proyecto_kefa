
package com.software.kefa.service.modelosto;

public class MetodoPagoTO {
    private String numeroTarjeta;
    private String fechaVencimiento;
    private String nombreTarjeta;
    private String cvv;

    public MetodoPagoTO() {
    }

    public MetodoPagoTO(String numeroTarjeta, String fechaVencimiento, String nombreTarjeta, String cvv) {
        this.numeroTarjeta = numeroTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.nombreTarjeta = nombreTarjeta;
        this.cvv = cvv;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    
}
