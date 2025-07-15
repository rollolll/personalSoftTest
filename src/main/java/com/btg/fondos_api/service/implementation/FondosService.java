package com.btg.fondos_api.service.implementation;

import com.btg.fondos_api.dto.*;
import com.btg.fondos_api.exception.ErrorGenericoException;
import com.btg.fondos_api.exception.RecursoNoEncontradoException;
import com.btg.fondos_api.mapper.FondoMapper;
import com.btg.fondos_api.persistence.model.ClienteModel;
import com.btg.fondos_api.persistence.model.FondoModel;
import com.btg.fondos_api.persistence.repository.ClienteRepository;
import com.btg.fondos_api.persistence.repository.FondoRepository;
import com.btg.fondos_api.service.IFondoService;
import com.btg.fondos_api.service.ISuscripcionService;
import com.btg.fondos_api.service.ITransaccionService;
import com.btg.fondos_api.utilities.ConstantesAplicacion;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class FondosService implements IFondoService {

    private final FondoRepository fondoRepository;
    private final FondoMapper fondoMapper;
    private final ClienteRepository clienteRepository;
    private final ITransaccionService iTransaccionService;
    private final ISuscripcionService iSuscripcionService;

    public FondosService(FondoRepository fondoRepository, FondoMapper fondoMapper,
                         ClienteRepository clienteRepository,
                         ITransaccionService iTransaccionService, ISuscripcionService iSuscripcionService) {
        this.fondoRepository = fondoRepository;
        this.fondoMapper = fondoMapper;
        this.clienteRepository = clienteRepository;
        this.iTransaccionService = iTransaccionService;
        this.iSuscripcionService = iSuscripcionService;
    }

    @Override
    public ApiResponseDto obtenerFondos() {

        List<FondoModel> fondos = fondoRepository.findAll();
        if (fondos.isEmpty()) {
            throw new RecursoNoEncontradoException(ConstantesAplicacion.ERROR_NO_SE_ENCUENTRAN_FONDOS);
        }
        List<FondoDto> fondosMap = fondoMapper.toDtoList(fondos);
        return ApiResponseDto.builder()
                .error(false)
                .codigoResultado(HttpStatus.OK.value())
                .objeto(fondosMap)
                .build();
    }

    @Override
    public ApiResponseDto suscribirFondo(SuscribirFondoRequest fondoRequest) throws Exception {
        ClienteModel cliente = clienteRepository.findById(fondoRequest.getIdCliente())
                .orElseThrow(() -> new RecursoNoEncontradoException(HttpStatus.NOT_FOUND.getReasonPhrase()));

        FondoModel fondo = fondoRepository.findById(fondoRequest.getIdFondo())
                .orElseThrow(() -> new RecursoNoEncontradoException(HttpStatus.NOT_FOUND.getReasonPhrase()));

        ApiResponseDto<SuscripcionDto> resultadoSuscripcion = iSuscripcionService.ejecutarSuscripcion(cliente, fondo);

        if (!resultadoSuscripcion.getCodigoResultado().equals(HttpStatus.OK.value())) {
            throw new ErrorGenericoException("Fallo al realizar la suscripcion");
        }

        try {
            ApiResponseDto resultadoTransaccion = iTransaccionService.registrarTransaccion(
                    construirTransaccion(fondoRequest, fondo));
            if (!resultadoTransaccion.getCodigoResultado().equals(HttpStatus.OK.value())) {
                iSuscripcionService.eliminarSuscripcion(resultadoSuscripcion.getObjeto().getId());
            }
        } catch (Exception e) {
            iSuscripcionService.eliminarSuscripcion(resultadoSuscripcion.getObjeto().getId());
        }

        return ApiResponseDto.builder()
                .error(false)
                .codigoResultado(HttpStatus.OK.value())
                .mensaje("Suscripcion Exitosa")
                .build();
    }

    private RegistrarTransaccionDto construirTransaccion(SuscribirFondoRequest fondoRequest, FondoModel fondo) {
        return RegistrarTransaccionDto.builder()
                .clienteId(fondoRequest.getIdCliente())
                .fondoId(fondoRequest.getIdFondo())
                .tipo("APORTE")
                .monto(fondo.getMontoMinimo())
                .fecha(Instant.now())
                .descripcion("Suscripcion a fondo")
                .build();
    }
}