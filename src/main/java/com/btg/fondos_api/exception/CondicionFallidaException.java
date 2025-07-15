package com.btg.fondos_api.exception;

public class CondicionFallidaException extends RuntimeException {
    public CondicionFallidaException(String mensaje) {
        super(mensaje);
    }
}
