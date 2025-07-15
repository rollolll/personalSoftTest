package com.btg.fondos_api.controller;

import com.btg.fondos_api.dto.ApiResponseDto;
import com.btg.fondos_api.dto.FondoDto;
import com.btg.fondos_api.service.IFondoService;
import com.btg.fondos_api.utilities.ConstantesAplicacion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Clase que implementa los servicios REST de consultar, actualizar y eliminar fondos
 *
 * @since 1.0.0
 */
@RestController
@RequestMapping(ConstantesAplicacion.REQUEST_PATH_FONDO)
public class FondoController {
    private final IFondoService iFondoService;

    public FondoController(IFondoService iFondoService) {
        this.iFondoService = iFondoService;
    }

    @GetMapping(ConstantesAplicacion.PATH_OBTENER_FONDOS)
    public ResponseEntity<ApiResponseDto<List<FondoDto>>> obtenerTodos() {
        return ResponseEntity.ok(this.iFondoService.obtenerFondos());
    }
}
