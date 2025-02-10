package com.example.demo.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        // 스웨거 info 설정
        Info info = new Info().title("CakeWay API Documentation")
                .version("1.0")
                .description("API documentation for the CakeWay API Documentation.");

        SecurityScheme apiKey = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Bearer");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Bearer Token", apiKey))
                .addSecurityItem(securityRequirement);

    }


}
