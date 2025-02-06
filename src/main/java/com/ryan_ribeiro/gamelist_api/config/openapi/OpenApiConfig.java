package com.ryan_ribeiro.gamelist_api.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("GameList - API")
                        .description("API de uma coleção de jogos")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Ryan Ribeiro")
                                .email("RyanRodrigues0071234@gmail.com")
                                .url("")
                        )
                );
    }
}
