package com.btg.fondos_api.controller;

import com.btg.fondos_api.dto.AuthResponse;
import com.btg.fondos_api.dto.LoginRequest;
import com.btg.fondos_api.dto.RegistrarRequest;
import com.btg.fondos_api.service.IAuthService;
import com.btg.fondos_api.utilities.ConstantesAplicacion;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ConstantesAplicacion.REQUEST_PATH_AUTH)
@RequiredArgsConstructor
@Tag(name = "Autenticacion Service")
public class AutenticacionController {

    private final IAuthService authService;

    @PostMapping(ConstantesAplicacion.PATH_LOGEAR_USUARIO)
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(ConstantesAplicacion.PATH_REGISTRAR_USUARIO)
    public ResponseEntity<AuthResponse> register(@RequestBody RegistrarRequest request) {
        authService.register(request);
        return ResponseEntity.noContent().build();
    }
}