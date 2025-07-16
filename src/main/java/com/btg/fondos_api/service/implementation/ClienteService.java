package com.btg.fondos_api.service.implementation;

import com.btg.fondos_api.dto.ApiResponseDto;
import com.btg.fondos_api.dto.ClienteDto;
import com.btg.fondos_api.dto.ClienteRequest;
import com.btg.fondos_api.exception.ErrorGenericoException;
import com.btg.fondos_api.exception.RecursoNoEncontradoException;
import com.btg.fondos_api.mapper.ClienteMapper;
import com.btg.fondos_api.persistence.model.ClienteModel;
import com.btg.fondos_api.persistence.repository.ClienteRepository;
import com.btg.fondos_api.service.IClienteService;
import com.btg.fondos_api.utilities.ConstantesAplicacion;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ApiResponseDto obtenerClientes() {
        List<ClienteModel> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) {
            throw new RecursoNoEncontradoException(ConstantesAplicacion.ERROR_NO_SE_ENCUENTRAN_CLIENTES);
        }
        List<ClienteRequest> clienteMap = clienteMapper.toDtoList(clientes);
        return ApiResponseDto.builder()
                .error(false)
                .codigoResultado(HttpStatus.OK.value())
                .objeto(clienteMap)
                .build();
    }

    @Override
    public ApiResponseDto crearCliente(ClienteRequest cliente) {
        try {
            ClienteModel model = clienteMapper.toModel(cliente);
            model.setSaldo(BigDecimal.valueOf(500000));
            model = clienteRepository.save(model);
            return ApiResponseDto.builder()
                    .error(false)
                    .codigoResultado(HttpStatus.OK.value())
                    .mensaje(ConstantesAplicacion.EXITOSO_CLIENTE)
                    .objeto(ClienteDto.builder().idCliente(model.getId()).build())
                    .build();
        } catch (ErrorGenericoException e) {
            throw new ErrorGenericoException(ConstantesAplicacion.ERROR_CREAR_CLIENTE);
        }
    }
}
