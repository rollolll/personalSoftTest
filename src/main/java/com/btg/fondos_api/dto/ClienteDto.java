package com.btg.fondos_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private String id;
    private String nombre;
    private String email;
    private String telefono;
    private String preferenciaNotificacion;
    private BigDecimal saldo;
}
