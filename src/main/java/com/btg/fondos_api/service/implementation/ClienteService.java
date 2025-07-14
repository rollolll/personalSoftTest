package com.btg.fondos_api.service.implementation;

import com.btg.fondos_api.dto.ApiResponseDto;
import com.btg.fondos_api.dto.ClienteDto;
import com.btg.fondos_api.mapper.ClienteMapper;
import com.btg.fondos_api.persistence.model.ClienteModel;
import com.btg.fondos_api.persistence.repository.ClienteRepository;
import com.btg.fondos_api.service.IClienteService;
import com.btg.fondos_api.utilities.ECodResultadoApiResponse;
import org.springframework.stereotype.Service;

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
        List<ClienteDto> clienteMap = clienteMapper.toDtoList(clientes);
        return ApiResponseDto.builder()
                .error(false)
                .codigoResultado(ECodResultadoApiResponse.OK.getCodigo())
                .objeto(clienteMap)
                .build();
    }
}
