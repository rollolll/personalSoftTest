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
public class FondoDto {
    private String id;
    private String nombre;
    private BigDecimal montoMinimo;
    private String categoria;

}
