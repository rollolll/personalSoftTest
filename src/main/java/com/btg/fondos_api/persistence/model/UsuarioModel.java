package com.btg.fondos_api.persistence.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioModel {
    @Id
    private String id;
    private String username;
    private String password;
    private List<String> roles;
    private boolean state;
}
