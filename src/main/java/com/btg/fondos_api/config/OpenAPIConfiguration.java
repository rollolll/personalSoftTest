package com.btg.fondos_api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Value("${swagger-access}")
    private String swaggerAccess;

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl(swaggerAccess);
        server.setDescription("Development");

        return new OpenAPI().addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info().title("My REST API")
                        .description("Some custom description of API.")
                        .version("1.0")
                        .contact(new Contact().name("Jorge Mario Vasquez")
                                .email("j-mario9715@hotmail.com"))
                        .license(new License().name("License of API")
                                .url("API license URL")))
                .servers(List.of(server));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}