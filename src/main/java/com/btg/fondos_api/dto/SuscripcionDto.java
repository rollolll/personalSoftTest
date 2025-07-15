package com.btg.fondos_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuscripcionDto {
    private String id;
    private String clienteId;
    private String fondoId;
    private Instant fechaSuscripcion;
    private boolean activa;
    private Instant fechaCancelacion;
    private BigDecimal valorSuscripcion;
}
