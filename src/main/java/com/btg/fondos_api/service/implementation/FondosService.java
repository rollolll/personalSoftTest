package com.btg.fondos_api.service.implementation;

import com.btg.fondos_api.dto.ApiResponseDto;
import com.btg.fondos_api.dto.FondoDto;
import com.btg.fondos_api.exception.RecursoNoEncontradoException;
import com.btg.fondos_api.mapper.FondoMapper;
import com.btg.fondos_api.persistence.model.FondoModel;
import com.btg.fondos_api.persistence.repository.FondoRepository;
import com.btg.fondos_api.service.IFondoService;
import com.btg.fondos_api.utilities.ConstantesAplicacion;
import com.btg.fondos_api.utilities.ECodResultadoApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FondosService implements IFondoService {

    private final FondoRepository fondoRepository;
    private final FondoMapper fondoMapper;

    public FondosService(FondoRepository fondoRepository, FondoMapper fondoMapper) {
        this.fondoRepository = fondoRepository;
        this.fondoMapper = fondoMapper;
    }

    @Override
    public ApiResponseDto obtenerFondos() {

        List<FondoModel> fondos = fondoRepository.findAll();
        if (fondos.isEmpty()){
            throw new RecursoNoEncontradoException(ConstantesAplicacion.ERROR_NO_SE_ENCUENTRAN_FONDOS);
        }
        List<FondoDto> fondosMap = fondoMapper.toDtoList(fondos);
        return ApiResponseDto.builder()
                .error(false)
                .codigoResultado(ECodResultadoApiResponse.OK.getCodigo())
                .objeto(fondosMap)
                .build();
    }


}
