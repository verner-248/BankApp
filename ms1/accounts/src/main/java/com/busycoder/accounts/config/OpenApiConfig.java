package com.busycoder.loans.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("BankApp API")
                        .description("API documentation for BankApp")
                        .version("v0.0.1"))
                .externalDocs(new ExternalDocumentation()
                        .description("BankApp Wiki Documentation")
                        .url("https://example.com/docs"));
    }
}
