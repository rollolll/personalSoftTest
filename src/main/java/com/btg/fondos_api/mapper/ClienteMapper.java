package com.btg.fondos_api.mapper;

import com.btg.fondos_api.dto.ClienteDto;
import com.btg.fondos_api.persistence.model.ClienteModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDto toDto(ClienteModel cliente);
    ClienteModel toModel(ClienteDto cliente);
    List<ClienteDto> toDtoList(List<ClienteModel> clientes);
}
