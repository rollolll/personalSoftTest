package com.btg.fondos_api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T>{
    private boolean error;
    private Integer codigoResultado;
    private String mensaje;
    private T objeto;
}
