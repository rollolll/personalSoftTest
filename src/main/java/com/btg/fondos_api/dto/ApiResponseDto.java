package com.btg.fondos_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto<T>{
    private boolean error;
    private Integer codigoResultado;
    private String mensaje;
    private T objeto;
}