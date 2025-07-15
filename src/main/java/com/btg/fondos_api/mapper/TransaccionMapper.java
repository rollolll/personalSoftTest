package com.btg.fondos_api.mapper;

import com.btg.fondos_api.dto.RegistrarTransaccionDto;
import com.btg.fondos_api.persistence.model.TransaccionModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransaccionMapper {
    TransaccionModel toDto(RegistrarTransaccionDto transaccion);
}
