package com.btg.fondos_api.service.implementation;

import com.btg.fondos_api.dto.ApiResponseDto;
import com.btg.fondos_api.dto.RegistrarTransaccionDto;
import com.btg.fondos_api.exception.ErrorGenericoException;
import com.btg.fondos_api.mapper.TransaccionMapper;
import com.btg.fondos_api.persistence.model.TransaccionModel;
import com.btg.fondos_api.persistence.repository.TransaccionRepository;
import com.btg.fondos_api.service.ITransaccionService;
import com.btg.fondos_api.utilities.ConstantesAplicacion;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TransaccionService implements ITransaccionService {

    private TransaccionRepository transaccionRepository;
    private TransaccionMapper transaccionMapper;

    public TransaccionService(TransaccionRepository transaccionRepository, TransaccionMapper transaccionMapper) {
        this.transaccionRepository = transaccionRepository;
        this.transaccionMapper = transaccionMapper;
    }

    @Override
    public ApiResponseDto registrarTransaccion(RegistrarTransaccionDto transaccion) {
        try {
            TransaccionModel model = transaccionMapper.toDto(transaccion);
            transaccionRepository.save(model);
            return ApiResponseDto.builder()
                    .codigoResultado(HttpStatus.OK.value())
                    .error(false)
                    .build();
        } catch (ErrorGenericoException e) {
            throw new ErrorGenericoException(ConstantesAplicacion.ERROR_CREAR_TRANSACCION);
        }
    }
}
