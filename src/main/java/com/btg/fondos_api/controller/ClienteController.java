package com.btg.fondos_api.controller;

import com.btg.fondos_api.dto.ApiResponseDto;
import com.btg.fondos_api.dto.ClienteDto;
import com.btg.fondos_api.service.IClienteService;
import com.btg.fondos_api.utilities.ConstantesAplicacion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Clase que implementa los servicios REST de consultar, actualizar y eliminar clientes
 *
 * @since 1.0.0
 */
@RestController
@RequestMapping(ConstantesAplicacion.REQUEST_PATH_CLIENTE)
public class ClienteController {

    private final IClienteService iClienteService;

    public ClienteController(IClienteService iClienteService) {
        this.iClienteService = iClienteService;
    }

    @GetMapping(ConstantesAplicacion.PATH_OBTENER_CLIENTE)
    public ResponseEntity<ApiResponseDto<List<ClienteDto>>> obtenerTodos() {
        return ResponseEntity.ok(this.iClienteService.obtenerClientes());
    }
}
