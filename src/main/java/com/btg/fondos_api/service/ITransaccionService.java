package com.btg.fondos_api.service;

import com.btg.fondos_api.dto.ApiResponseDto;
import com.btg.fondos_api.dto.RegistrarTransaccionDto;

public interface ITransaccionService {
    ApiResponseDto registrarTransaccion(RegistrarTransaccionDto transaccion);
}
