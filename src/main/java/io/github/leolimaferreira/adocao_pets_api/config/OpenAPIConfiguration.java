package io.github.leolimaferreira.adocao_pets_api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Adoção de Pets API",
                version = "v1",
                contact = @Contact(
                        name = "Leonardo Lima",
                        email = "leonardo.limaferreira718@gmail.com"
                )
        )
)
public class OpenAPIConfiguration {
}
