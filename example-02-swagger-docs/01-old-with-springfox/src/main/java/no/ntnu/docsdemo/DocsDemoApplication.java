package no.ntnu.docsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@SpringBootApplication
public class DocsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocsDemoApplication.class, args);
    }

    /**
     * Create configuration for Swagger - turn off some Spring-default APIs etc
     * @return Swagger config
     */
    @Bean
    public Docket getSwaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // We can say that we want to include only APIs under a specific URL. But we skip that, because
                // here all the APIs which are created by us use the no.ntnu package
                // See some examples of URL matching rules here:
                // https://stackoverflow.com/questions/2952196/ant-path-style-patterns
//				.paths(PathSelectors.ant("/products/**"))
                .apis(RequestHandlerSelectors.basePackage("no.ntnu"))
                .build()
                .apiInfo(describeApi());
    }

    private ApiInfo describeApi() {
        return new ApiInfo(
                "AppDev example 02",
                "Example on how to generate Swagger documentation for a Spring Boot project. Used in course IDATA2306 Application development",
                "1.0",
                "https://github.com/strazdinsg/app-dev",
                new Contact("Girts Strazdins", "https://github.com/strazdinsg", null),
                "MIT License",
                "https://github.com/strazdinsg/app-dev/blob/main/LICENSE",
                Collections.emptyList()
        );
    }

}
