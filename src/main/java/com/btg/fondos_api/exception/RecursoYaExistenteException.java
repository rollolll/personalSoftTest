package com.btg.fondos_api.exception;

public class RecursoYaExistenteException extends RuntimeException {

    public RecursoYaExistenteException(String mensaje) {
        super(mensaje);
    }
}