package com.btg.fondos_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuscribirFondoRequest {
    private String idCliente;
    private String idFondo;
}
