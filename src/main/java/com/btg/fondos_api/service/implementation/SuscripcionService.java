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
import com.btg.fondos_api.service.ISuscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SuscripcionService implements ISuscripcionService {

    private final SuscripcionRepository suscripcionRepository;
    private final ClienteRepository clienteRepository;
    private final TransaccionRepository transaccionRepository;

    @Override
    public ApiResponseDto<SuscripcionDto> ejecutarSuscripcion(ClienteModel cliente, FondoModel fondo) {

        if (Objects.isNull(cliente)||Objects.isNull(fondo)){
            throw new CondicionFallidaException("Error al procesar la suscripcion");
        }

        if (cliente.getSaldo().compareTo(fondo.getMontoMinimo()) < 0) {
            throw new CondicionFallidaException(String.format(
                    "No tiene saldo disponible para vincularse al fondo <%s>", fondo.getNombre()));
        }
        Optional<SuscripcionModel> validacionExistente = suscripcionRepository.findByClienteIdAndFondoIdAndActiva(cliente.getId(), fondo.getId(),true);
        if (validacionExistente.isPresent()){
            throw new CondicionFallidaException(String.format(
                    "Ya tiene una suscripcion al fondo <%s>", fondo.getNombre()));
        }

        SuscripcionModel nuevaSuscripcion = SuscripcionModel.builder()
                .clienteId(cliente.getId())
                .fondoId(fondo.getId())
                .valorSuscripcion(fondo.getMontoMinimo())
                .activa(true)
                .fechaSuscripcion(Instant.now())
                .build();

        SuscripcionModel suscripcionGuardada = suscripcionRepository.save(nuevaSuscripcion);

        cliente.setSaldo(cliente.getSaldo().subtract(fondo.getMontoMinimo()));
        clienteRepository.save(cliente);

        SuscripcionDto suscripcionDto = SuscripcionDto.builder()
                .id(suscripcionGuardada.getId())
                .clienteId(suscripcionGuardada.getClienteId())
                .fondoId(suscripcionGuardada.getFondoId())
                .valorSuscripcion(suscripcionGuardada.getValorSuscripcion())
                .activa(suscripcionGuardada.isActiva())
                .fechaSuscripcion(suscripcionGuardada.getFechaSuscripcion())
                .build();

        return ApiResponseDto.<SuscripcionDto>builder()
                .error(false)
                .codigoResultado(HttpStatus.OK.value())
                .mensaje("Suscripción realizada correctamente.")
                .objeto(suscripcionDto)
                .build();
    }

    @Override
    public void eliminarSuscripcion(String idSuscripcion) {
        try {
            suscripcionRepository.deleteById(idSuscripcion);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ApiResponseDto<Void> cancelarSuscripcion(String idSuscripcion) {

        SuscripcionModel suscripcion = suscripcionRepository.findById(idSuscripcion)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Suscripción con id " + idSuscripcion + " no encontrada"));

        String idCliente = suscripcion.getClienteId();
        BigDecimal monto = suscripcion.getValorSuscripcion();

        suscripcion.setActiva(false);
        suscripcionRepository.save(suscripcion);

        ClienteModel cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Cliente con id " + idCliente + " no encontrado"));

        cliente.setSaldo(cliente.getSaldo().add(monto));
        clienteRepository.save(cliente);

        TransaccionModel estorno = TransaccionModel.builder()
                .clienteId(idCliente)
                .fondoId(suscripcion.getFondoId())
                .monto(monto)
                .tipo("ESTORNO_SUSCRIPCION")
                .descripcion("Reverso automático de la suscripción fallida")
                .build();

        transaccionRepository.save(estorno);

        return ApiResponseDto.<Void>builder()
                .error(false)
                .codigoResultado(HttpStatus.OK.value())
                .mensaje("La suscripción fue eliminada correctamente.")
                .objeto(null)
                .build();
    }
}
