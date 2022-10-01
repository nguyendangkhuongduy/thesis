package com.duy.thesisManagement.thesis.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
		info = @Info(
				description = "All supported thesis management resources",
				version = "V1.0.0",
				title = "Thesis management API",
				contact = @Contact(
						name = "Khuong Duy",
						email = "khuongduy2401@gmail.com"
				),
				extensions = {
						@Extension(
								properties = {
										@ExtensionProperty(name = "technologies",
												value = "java11, Mysql, Spring,"
														+ "Spring Boot, Hibernate")
								})
				}
		)
)
@Configuration
public class SwaggerConfig {

}
