package com.btg.fondos_api.service.implementation;

import com.btg.fondos_api.dto.ApiResponseDto;
import com.btg.fondos_api.dto.SuscripcionDto;
import com.btg.fondos_api.exception.CondicionFallidaException;
import com.btg.fondos_api.exception.RecursoNoEncontradoException;
import com.btg.fondos_api.persistence.model.ClienteModel;
import com.btg.fondos_api.persistence.model.FondoModel;
import com.btg.fondos_api.persistence.model.SuscripcionModel;
import com.btg.fondos_api.persistence.model.TransaccionModel;
import com.btg.fondos_api.persistence.repository.ClienteRepository;
import com.btg.fondos_api.persistence.repository.SuscripcionRepository;
import com.btg.fondos_api.persistence.repository.TransaccionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SuscripcionServiceTest {

    @Mock
    private SuscripcionRepository mockSuscripcionRepository;
    @Mock
    private ClienteRepository mockClienteRepository;
    @Mock
    private TransaccionRepository mockTransaccionRepository;

    private SuscripcionService suscripcionServiceUnderTest;

    @BeforeEach
    void setUp() {
        suscripcionServiceUnderTest = new SuscripcionService(mockSuscripcionRepository, mockClienteRepository,
                mockTransaccionRepository);
    }

    @Test
    void testEjecutarSuscripcionFail() {
        final ClienteModel cliente = new ClienteModel();
        cliente.setId("clienteId");
        cliente.setNombre("nombre");
        cliente.setEmail("email");
        cliente.setTelefono("telefono");
        cliente.setSaldo(new BigDecimal("0.00"));

        final FondoModel fondo = new FondoModel("fondoId", "nombre", new BigDecimal("0.00"), "categoria");

        final Optional<SuscripcionModel> suscripcionModel = Optional.of(SuscripcionModel.builder()
                .id("id")
                .clienteId("clienteId")
                .fondoId("fondoId")
                .fechaSuscripcion(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .activa(false)
                .valorSuscripcion(new BigDecimal("0.00"))
                .build());
        when(mockSuscripcionRepository.findByClienteIdAndFondoIdAndActiva("clienteId", "fondoId", true))
                .thenReturn(suscripcionModel);

        assertThatThrownBy(() -> suscripcionServiceUnderTest.ejecutarSuscripcion(cliente, fondo))
                .isInstanceOf(CondicionFallidaException.class);
    }

    @Test
    void testEjecutarSuscripcionFail2() {
        final ClienteModel cliente = new ClienteModel();
        cliente.setId("clienteId");
        cliente.setNombre("nombre");
        cliente.setEmail("email");
        cliente.setTelefono("telefono");
        cliente.setSaldo(new BigDecimal("0.00"));

        final FondoModel fondo = new FondoModel("fondoId", "nombre", new BigDecimal("0.00"), "categoria");
        when(mockSuscripcionRepository.findByClienteIdAndFondoIdAndActiva("clienteId", "fondoId", true))
                .thenReturn(Optional.empty());

        final SuscripcionModel suscripcionModel = SuscripcionModel.builder()
                .id("id")
                .clienteId("clienteId")
                .fondoId("fondoId")
                .fechaSuscripcion(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .activa(false)
                .valorSuscripcion(new BigDecimal("0.00"))
                .build();
        when(mockSuscripcionRepository.save(any(SuscripcionModel.class))).thenReturn(suscripcionModel);

        final ApiResponseDto<SuscripcionDto> result = suscripcionServiceUnderTest.ejecutarSuscripcion(cliente, fondo);
        assertNotNull(result);

        verify(mockClienteRepository).save(any(ClienteModel.class));
    }

    @Test
    void testEliminarSuscripcion() {
        suscripcionServiceUnderTest.eliminarSuscripcion("idSuscripcion");

        verify(mockSuscripcionRepository).deleteById("idSuscripcion");
    }

    @Test
    void testCancelarSuscripcion() {
        final Optional<SuscripcionModel> suscripcionModel = Optional.of(SuscripcionModel.builder()
                .id("id")
                .clienteId("clienteId")
                .fondoId("fondoId")
                .fechaSuscripcion(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .activa(false)
                .valorSuscripcion(new BigDecimal("0.00"))
                .build());
        when(mockSuscripcionRepository.findById("idSuscripcion")).thenReturn(suscripcionModel);

        final ClienteModel clienteModel1 = new ClienteModel();
        clienteModel1.setId("clienteId");
        clienteModel1.setNombre("nombre");
        clienteModel1.setEmail("email");
        clienteModel1.setTelefono("telefono");
        clienteModel1.setSaldo(new BigDecimal("0.00"));
        final Optional<ClienteModel> clienteModel = Optional.of(clienteModel1);
        when(mockClienteRepository.findById("clienteId")).thenReturn(clienteModel);

        final ApiResponseDto<Void> result = suscripcionServiceUnderTest.cancelarSuscripcion("idSuscripcion");
        assertNotNull(result);

        verify(mockSuscripcionRepository).save(any(SuscripcionModel.class));
        verify(mockClienteRepository).save(any(ClienteModel.class));
        verify(mockTransaccionRepository).save(any(TransaccionModel.class));
    }

    @Test
    void testCancelarSuscripcionFail() {
        when(mockSuscripcionRepository.findById("idSuscripcion")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> suscripcionServiceUnderTest.cancelarSuscripcion("idSuscripcion"))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }

    @Test
    void testCancelarSuscripcionFail2() {

        final Optional<SuscripcionModel> suscripcionModel = Optional.of(SuscripcionModel.builder()
                .id("id")
                .clienteId("clienteId")
                .fondoId("fondoId")
                .fechaSuscripcion(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC))
                .activa(false)
                .valorSuscripcion(new BigDecimal("0.00"))
                .build());
        when(mockSuscripcionRepository.findById("idSuscripcion")).thenReturn(suscripcionModel);

        when(mockClienteRepository.findById("clienteId")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> suscripcionServiceUnderTest.cancelarSuscripcion("idSuscripcion"))
                .isInstanceOf(RecursoNoEncontradoException.class);
        verify(mockSuscripcionRepository).save(any(SuscripcionModel.class));
    }
}
