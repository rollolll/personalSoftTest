package com.btg.fondos_api.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteModel {
    @Id
    private String id;
    private String nombre;
    private String email;
    private String telefono;
    private String preferenciaNotificacion;
    private BigDecimal saldo;
}
