package com.springboot.httpResponse.Config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Http Response",
                description = "The Project is about Exception handling and Http Response",
                version = "1.0.0",
                license = @License(
                        name = "SPRING-BOOT",
                        url = "www.google.com"
                ),
                contact = @Contact(
                        name = "Devendra"
                )
        )
)
public class SwaggerConfig {
}
