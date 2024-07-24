package com.software.kefa.excepcion;

/**
 * This exception is thrown when a message already exists.
 */
public class MensajeExisteExcepcion extends RuntimeException {
    /**
     * Constructs a new MensajeExisteExcepcion with the specified detail message.
     *
     * @param message the detail message
     */
    public MensajeExisteExcepcion(String message) {
        super(message);
    }
}
