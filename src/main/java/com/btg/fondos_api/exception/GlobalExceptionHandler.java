package com.btg.fondos_api.exception;

import com.btg.fondos_api.dto.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ApiResponseDto<String>> recursoNoEncontrado(RecursoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponseDto.<String>builder()
                        .error(true)
                        .codigoResultado(HttpStatus.NOT_FOUND.value())
                        .mensaje(ex.getMessage())
                        .objeto(null)
                        .build());
    }

    @ExceptionHandler(RecursoYaExistenteException.class)
    public ResponseEntity<ApiResponseDto<String>> recursoYaExistente(RecursoYaExistenteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponseDto.<String>builder()
                        .error(true)
                        .codigoResultado(HttpStatus.CONFLICT.value())
                        .mensaje(ex.getMessage())
                        .objeto(null)
                        .build());
    }

    @ExceptionHandler(ErrorGenericoException.class)
    public ResponseEntity<ApiResponseDto<String>> errorGenerico(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponseDto.<String>builder()
                        .error(true)
                        .codigoResultado(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .mensaje("Error inesperado: " + ex.getMessage())
                        .objeto(null)
                        .build());
    }

    @ExceptionHandler(CondicionFallidaException.class)
    public ResponseEntity<ApiResponseDto<String>> condicionFallida(Exception ex) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                .body(ApiResponseDto.<String>builder()
                        .error(true)
                        .codigoResultado(HttpStatus.PRECONDITION_FAILED.value())
                        .mensaje(ex.getMessage())
                        .objeto(null)
                        .build());
    }

}
