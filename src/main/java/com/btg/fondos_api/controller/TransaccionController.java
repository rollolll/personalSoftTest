package com.btg.fondos_api.controller;

import com.btg.fondos_api.utilities.ConstantesAplicacion;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase que implementa los servicios REST de consultar, actualizar y eliminar transacciones
 *
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(ConstantesAplicacion.REQUEST_PATH_TRANSACCION)
public class TransaccionController {
}
