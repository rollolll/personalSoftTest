package com.btg.fondos_api.controller;

import com.btg.fondos_api.dto.ApiResponseDto;
import com.btg.fondos_api.dto.ClienteRequest;
import com.btg.fondos_api.service.IClienteService;
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
class ClienteControllerTest {


    @Mock
    private IClienteService mockIClienteService;

    @InjectMocks
    private ClienteController clienteController;

    @Test
    void testObtenerTodos() {
        ApiResponseDto<List<ClienteRequest>> apiResponseDto = ApiResponseDto.<List<ClienteRequest>>builder()
                .error(false)
                .codigoResultado(200)
                .mensaje("Consulta exitosa")
                .build();

        when(mockIClienteService.obtenerClientes()).thenReturn(apiResponseDto);

        ResponseEntity<ApiResponseDto<List<ClienteRequest>>> response =
                clienteController.obtenerTodos();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testCrearCliente() {

        ResponseEntity<ApiResponseDto> response =
                clienteController.crearCliente(new ClienteRequest());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
