package com.btg.fondos_api.service;

import com.btg.fondos_api.dto.ApiResponseDto;
import com.btg.fondos_api.dto.SuscripcionDto;
import com.btg.fondos_api.persistence.model.ClienteModel;
import com.btg.fondos_api.persistence.model.FondoModel;

public interface ISuscripcionService {

    ApiResponseDto<SuscripcionDto> ejecutarSuscripcion(ClienteModel cliente, FondoModel fondo);
    void eliminarSuscripcion(String idSuscripcion);
    ApiResponseDto cancelarSuscripcion(String idSuscripcion);
}
