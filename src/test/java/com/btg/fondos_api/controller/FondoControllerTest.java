package com.btg.fondos_api.controller;

import com.btg.fondos_api.dto.ApiResponseDto;
import com.btg.fondos_api.dto.ClienteDto;
import com.btg.fondos_api.dto.FondoDto;
import com.btg.fondos_api.dto.SuscribirFondoRequest;
import com.btg.fondos_api.service.IFondoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FondoControllerTest {


    @Mock
    private IFondoService mockIFondoService;

    @InjectMocks
    private FondoController fondoController;

    @Test
    void testObtenerFondos() {
        ApiResponseDto<List<ClienteDto>> apiResponseDto = ApiResponseDto.<List<ClienteDto>>builder()
                .error(false)
                .codigoResultado(200)
                .mensaje("Consulta exitosa")
                .build();

        when(mockIFondoService.obtenerFondos()).thenReturn(apiResponseDto);

        ResponseEntity<ApiResponseDto<List<FondoDto>>> response =
                fondoController.obtenerTodos();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testSuscribirFondo() throws Exception {

        ResponseEntity<ApiResponseDto> response =
                fondoController.suscribirFondo(new SuscribirFondoRequest());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}