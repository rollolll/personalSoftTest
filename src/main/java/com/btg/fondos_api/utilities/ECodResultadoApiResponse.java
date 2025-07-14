package com.btg.fondos_api.utilities;

public enum ECodResultadoApiResponse {

    OK(200, "Operación exitosa");

    private final Integer codigo;
    private final String descripcion;

    ECodResultadoApiResponse(Integer codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}