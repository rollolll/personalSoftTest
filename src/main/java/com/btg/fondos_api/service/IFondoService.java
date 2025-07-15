package com.btg.fondos_api.service;

import com.btg.fondos_api.dto.ApiResponseDto;
import com.btg.fondos_api.dto.SuscribirFondoRequest;

/**
 * Interfaz con todos los métodos disponibles de fondos
 * @since 1.0.0
 */
public interface IFondoService {

    ApiResponseDto obtenerFondos();
    ApiResponseDto suscribirFondo(SuscribirFondoRequest fondo) throws Exception;
}
