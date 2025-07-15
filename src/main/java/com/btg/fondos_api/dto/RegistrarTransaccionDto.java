package com.btg.fondos_api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrarTransaccionDto {
    private String clienteId;
    private String fondoId;
    private String tipo;
    private BigDecimal monto;
    private Instant fecha;
    private String descripcion;
}
