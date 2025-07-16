package com.btg.fondos_api.mapper;

import com.btg.fondos_api.dto.ClienteRequest;
import com.btg.fondos_api.persistence.model.ClienteModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteRequest toDto(ClienteModel cliente);
    ClienteModel toModel(ClienteRequest cliente);
    List<ClienteRequest> toDtoList(List<ClienteModel> clientes);
}
