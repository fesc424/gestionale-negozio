package com.francescoquarra.gestionale_negozio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestionale Negozio API")
                        .version("1.0.0")
                        .description("API REST per la gestione di prodotti, magazzino, clienti e vendite di un negozio di prodotti di prima necessità."));
    }
}