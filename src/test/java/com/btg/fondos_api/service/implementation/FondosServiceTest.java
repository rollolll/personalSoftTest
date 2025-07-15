package com.btg.fondos_api.service.implementation;

import com.btg.fondos_api.dto.*;
import com.btg.fondos_api.exception.RecursoNoEncontradoException;
import com.btg.fondos_api.mapper.FondoMapper;
import com.btg.fondos_api.notification.INotificacionService;
import com.btg.fondos_api.persistence.model.ClienteModel;
import com.btg.fondos_api.persistence.model.FondoModel;
import com.btg.fondos_api.persistence.repository.ClienteRepository;
import com.btg.fondos_api.persistence.repository.FondoRepository;
import com.btg.fondos_api.service.ISuscripcionService;
import com.btg.fondos_api.service.ITransaccionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FondosServiceTest {

    @Mock
    private FondoRepository mockFondoRepository;
    @Mock
    private FondoMapper mockFondoMapper;
    @Mock
    private ClienteRepository mockClienteRepository;
    @Mock
    private ITransaccionService mockITransaccionService;
    @Mock
    private ISuscripcionService mockISuscripcionService;
    @Mock
    private INotificacionService mockINotificacionService;

    private FondosService fondosServiceUnderTest;

    @BeforeEach
    void setUp() {
        fondosServiceUnderTest = new FondosService(mockFondoRepository, mockFondoMapper, mockClienteRepository,
                mockITransaccionService, mockISuscripcionService, mockINotificacionService);
    }

    @Test
    void testObtenerFondos() {
        BigDecimal valor = BigDecimal.valueOf(500000);
        List<FondoModel> fondos = List.of(new FondoModel("id", "nombre", valor, "categoria"));
        List<FondoDto> fondoDtos = List.of(new FondoDto("id", "nombre", valor, "categoria"));

        when(mockFondoRepository.findAll()).thenReturn(fondos);
        when(mockFondoMapper.toDtoList(fondos)).thenReturn(fondoDtos);

        ApiResponseDto<?> result = fondosServiceUnderTest.obtenerFondos();
        assertNotNull(result);
    }
    @Test
    void testObtenerFondosFail() {
        when(mockFondoRepository.findAll()).thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> fondosServiceUnderTest.obtenerFondos())
                .isInstanceOf(RecursoNoEncontradoException.class);
    }

    @Test
    void testSuscribirFondo() throws Exception {
        final SuscribirFondoRequest fondoRequest = SuscribirFondoRequest.builder()
                .idCliente("clienteId")
                .idFondo("fondoId")
                .build();

        final ClienteModel clienteModel1 = new ClienteModel();
        clienteModel1.setId("id");
        clienteModel1.setNombre("nombre");
        clienteModel1.setEmail("email");
        clienteModel1.setTelefono("telefono");
        clienteModel1.setPreferenciaNotificacion("SMS");
        final Optional<ClienteModel> clienteModel = Optional.of(clienteModel1);
        when(mockClienteRepository.findById("clienteId")).thenReturn(clienteModel);

        final Optional<FondoModel> fondoModel = Optional.of(
                new FondoModel("id", "nombre", new BigDecimal("0.00"), "categoria"));
        when(mockFondoRepository.findById("fondoId")).thenReturn(fondoModel);

        final ApiResponseDto<SuscripcionDto> suscripcionDtoApiResponseDto = new ApiResponseDto<>(false, 200, "mensaje",
                SuscripcionDto.builder()
                        .id("id")
                        .build());
        when(mockISuscripcionService.ejecutarSuscripcion(any(ClienteModel.class), any(FondoModel.class)))
                .thenReturn(suscripcionDtoApiResponseDto);

        final ApiResponseDto apiResponseDto = new ApiResponseDto<>(false, 201, "mensaje", "objeto");
        when(mockITransaccionService.registrarTransaccion(any(RegistrarTransaccionDto.class)))
                .thenReturn(apiResponseDto);

        final ApiResponseDto result = fondosServiceUnderTest.suscribirFondo(fondoRequest);

        assertNotNull(result);
        verify(mockISuscripcionService).eliminarSuscripcion("id");
        verify(mockINotificacionService).enviarSms("telefono", "nombre");
    }

    @Test
    void testSuscribirFondoFail2() {
        final SuscribirFondoRequest fondoRequest = SuscribirFondoRequest.builder()
                .idCliente("clienteId")
                .idFondo("fondoId")
                .build();
        when(mockClienteRepository.findById("clienteId")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> fondosServiceUnderTest.suscribirFondo(fondoRequest))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }

    @Test
    void testSuscribirFondoFail() {
        final SuscribirFondoRequest fondoRequest = SuscribirFondoRequest.builder()
                .idCliente("clienteId")
                .idFondo("fondoId")
                .build();

        final ClienteModel clienteModel1 = new ClienteModel();
        clienteModel1.setId("id");
        clienteModel1.setNombre("nombre");
        clienteModel1.setEmail("email");
        clienteModel1.setTelefono("telefono");
        clienteModel1.setPreferenciaNotificacion("preferenciaNotificacion");
        final Optional<ClienteModel> clienteModel = Optional.of(clienteModel1);
        when(mockClienteRepository.findById("clienteId")).thenReturn(clienteModel);

        when(mockFondoRepository.findById("fondoId")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> fondosServiceUnderTest.suscribirFondo(fondoRequest))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }
}
