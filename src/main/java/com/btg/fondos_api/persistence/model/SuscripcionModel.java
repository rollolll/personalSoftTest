package com.btg.fondos_api.persistence.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "suscripciones")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SuscripcionModel {
    @Id
    private String id;
    private String clienteId;
    private String fondoId;
    private Instant fechaSuscripcion;
    private boolean activa;
    private Instant fechaCancelacion;
    private BigDecimal valorSuscripcion;
}
