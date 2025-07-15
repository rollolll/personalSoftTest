package com.btg.fondos_api.exception;

public class ErrorGenericoException extends RuntimeException {
    public ErrorGenericoException(String mensaje) {
        super(mensaje);
    }
}
