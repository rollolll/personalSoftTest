package com.btg.fondos_api.service.implementation;

import com.btg.fondos_api.dto.ApiResponseDto;
import com.btg.fondos_api.dto.ClienteRequest;
import com.btg.fondos_api.exception.RecursoNoEncontradoException;
import com.btg.fondos_api.mapper.ClienteMapper;
import com.btg.fondos_api.persistence.model.ClienteModel;
import com.btg.fondos_api.persistence.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository mockClienteRepository;
    @Mock
    private ClienteMapper mockClienteMapper;

    private ClienteService clienteServiceUnderTest;

    @BeforeEach
    void setUp() {
        clienteServiceUnderTest = new ClienteService(mockClienteRepository, mockClienteMapper);
    }

    @Test
    void testObtenerClientes() {
        final ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId("id");
        clienteModel.setNombre("nombre");
        clienteModel.setEmail("email@email.com");
        clienteModel.setTelefono("232154");
        clienteModel.setSaldo(new BigDecimal(500000));
        clienteModel.setPreferenciaNotificacion("SMS");
        final List<ClienteModel> clienteModels = List.of(clienteModel);
        when(mockClienteRepository.findAll()).thenReturn(clienteModels);

        final List<ClienteRequest> clienteRequests = List.of(
                new ClienteRequest("nombre", "documento", "email@email.com", "232154", "SMS"));
        when(mockClienteMapper.toDtoList(anyList())).thenReturn(clienteRequests);

        final ApiResponseDto result = clienteServiceUnderTest.obtenerClientes();
        assertNotNull(result);
    }

    @Test
    void testObtenerClientesFail() {
        when(mockClienteRepository.findAll()).thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> clienteServiceUnderTest.obtenerClientes())
                .isInstanceOf(RecursoNoEncontradoException.class);
    }

    @Test
    void testCrearCliente() {
        final ClienteRequest cliente = new ClienteRequest("nombre", "documento", "email", "telefono",
                "preferenciaNotificacion");

        final ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId("id");
        clienteModel.setNombre("nombre");
        clienteModel.setEmail("email");
        clienteModel.setTelefono("telefono");
        clienteModel.setSaldo(new BigDecimal("0.00"));
        when(mockClienteMapper.toModel(any(ClienteRequest.class))).thenReturn(clienteModel);
        when(mockClienteRepository.save(clienteModel)).thenReturn(clienteModel);

        final ApiResponseDto result = clienteServiceUnderTest.crearCliente(cliente);

        assertNotNull(result);
        verify(mockClienteRepository).save(any(ClienteModel.class));
    }
}
