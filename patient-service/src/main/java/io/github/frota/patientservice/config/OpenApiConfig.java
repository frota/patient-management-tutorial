package io.github.frota.patientservice.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public GroupedOpenApi groupMain() {
		return GroupedOpenApi.builder()
				.group("main")
				.pathsToMatch("/**")
				.build();
	}
	
	@Bean
	public OpenAPI appOpenAPI() {
		return new OpenAPI()
				.components(components())
				.info(info());
	}
	
	private Components components() {
		return new Components();
	}
	
	private Info info() {
		return new Info()
				.title("Patient Service API")
				.description("Patient Service API documentation")
				.version("0.1.0")
				.contact(contact());
	}
	
	private Contact contact() {
		return new Contact()
				.name("YouTube Tutorial")
				.url("https://www.youtube.com/watch?v=tseqdcFfTUY");
	}

}
