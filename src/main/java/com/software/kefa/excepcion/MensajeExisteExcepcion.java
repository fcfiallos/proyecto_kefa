package com.software.kefa.excepcion;

public class MensajeExisteExcepcion extends RuntimeException{
    public MensajeExisteExcepcion(String message) {
        super(message);
    }
}
