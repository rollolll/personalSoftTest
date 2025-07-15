package com.btg.fondos_api.service;

import com.btg.fondos_api.dto.ApiResponseDto;
import com.btg.fondos_api.dto.ClienteDto;

/**
 * Interfaz con todos los métodos disponibles de clientes
 * @since 1.0.0
 */
public interface IClienteService {

    ApiResponseDto obtenerClientes();
    ApiResponseDto crearCliente(ClienteDto cliente);
}
