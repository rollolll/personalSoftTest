package com.btg.fondos_api.mapper;

import com.btg.fondos_api.dto.FondoDto;
import com.btg.fondos_api.persistence.model.FondoModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FondoMapper {

    FondoDto toDto(FondoModel fondo);
    List<FondoDto> toDtoList(List<FondoModel> fondos);
}
