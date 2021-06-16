package com.tracker.entrypoint.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        String version = "1.0";

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(version))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tracker.entrypoint.controller"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/v*/**"))
                .build();

    }
    private ApiInfo apiInfo(String version) {
        return new ApiInfoBuilder()
                .title("Savings Microservice")
                .description("API services for handling customer savings goal and other saving features.")
                .version(version)
                .build();
    }
}
