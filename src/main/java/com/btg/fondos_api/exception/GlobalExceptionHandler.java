package com.btg.fondos_api.exception;

import com.btg.fondos_api.dto.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ApiResponseDto<String>> recursoNoEncontrado(RecursoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponseDto.<String>builder()
                        .error(true)
                        .codigoResultado(404)
                        .mensaje(ex.getMessage())
                        .objeto(null)
                        .build());
    }

    @ExceptionHandler(RecursoYaExistenteException.class)
    public ResponseEntity<ApiResponseDto<String>> recursoYaExistente(RecursoYaExistenteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponseDto.<String>builder()
                        .error(true)
                        .codigoResultado(409)
                        .mensaje(ex.getMessage())
                        .objeto(null)
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<String>> errorGenerico(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponseDto.<String>builder()
                        .error(true)
                        .codigoResultado(500)
                        .mensaje("Error inesperado: " + ex.getMessage())
                        .objeto(null)
                        .build());
    }
}
