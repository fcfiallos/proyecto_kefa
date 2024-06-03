package com.software.kefa.excepcion;

public class UsuarioExisteExcepcion extends RuntimeException{
    public UsuarioExisteExcepcion(String message) {
        super(message);
    }
}
