package com.btg.fondos_api.service.implementation;

import com.btg.fondos_api.dto.ApiResponseDto;
import com.btg.fondos_api.dto.RegistrarTransaccionDto;
import com.btg.fondos_api.mapper.TransaccionMapper;
import com.btg.fondos_api.persistence.model.TransaccionModel;
import com.btg.fondos_api.persistence.repository.TransaccionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransaccionServiceTest {

    @Mock
    private TransaccionRepository mockTransaccionRepository;
    @Mock
    private TransaccionMapper mockTransaccionMapper;
    @InjectMocks
    private TransaccionService transaccionServiceUnderTest;

    @BeforeEach
    void setUp() {
        transaccionServiceUnderTest = new TransaccionService(mockTransaccionRepository, mockTransaccionMapper);
    }

    @Test
    void testRegistrarTransaccion() {
        final RegistrarTransaccionDto transaccion = RegistrarTransaccionDto.builder().build();
        when(mockTransaccionMapper.toDto(any(RegistrarTransaccionDto.class)))
                .thenReturn(TransaccionModel.builder().build());

        final ApiResponseDto result = transaccionServiceUnderTest.registrarTransaccion(transaccion);

        assertNotNull(result);
        verify(mockTransaccionRepository).save(any(TransaccionModel.class));
    }
}
