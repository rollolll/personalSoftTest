package com.btg.fondos_api.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "fondos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FondoModel {
    @Id
    private String id;
    private String nombre;
    private BigDecimal montoMinimo;
    private String categoria;
}
