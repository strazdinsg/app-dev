package no.ntnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * The main application entrypoint
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * Create configuration for Swagger - turn off some Spring-default APIs etc
	 * @return Swagger config
	 */
	@Bean
	public Docket getSwaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("no.ntnu"))
				.build()
				.apiInfo(describeApi());
	}

	/**
	 * Describe the API (for Swagger)
	 * @return API description
	 */
	private ApiInfo describeApi() {
		return new ApiInfo(
				"AppDev example 06",
				"Example on how to dockerize Spring Boot applications. Used in course IDATA2306 Application development",
				"1.0",
				"https://github.com/strazdinsg/app-dev",
				new Contact("Girts Strazdins", "https://github.com/strazdinsg", null),
				"MIT License",
				"https://github.com/strazdinsg/app-dev/blob/main/LICENSE",
				Collections.emptyList()
		);
	}}
