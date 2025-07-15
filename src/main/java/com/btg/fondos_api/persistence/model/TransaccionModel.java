package com.btg.fondos_api.persistence.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "transacciones")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransaccionModel {
    @Id
    private String id;
    private String clienteId;
    private String fondoId;
    private String tipo;
    private BigDecimal monto;
    private Instant fecha;
    private String descripcion;
}
