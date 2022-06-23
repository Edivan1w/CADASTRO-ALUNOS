package br.com.sistemaEscola.cadastroAalunos.swagger;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@SecurityScheme(
		name = "bearerAuth",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "jwt",
		scheme = "Bearer"
		 
		)
public class SwaggerDocumentacao {

}
