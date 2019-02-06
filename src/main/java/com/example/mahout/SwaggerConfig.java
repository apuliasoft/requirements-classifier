package com.example.mahout;

import com.example.mahout.controller.ClassificationController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@PropertySource("classpath:swagger.properties")
@ComponentScan(basePackageClasses = ClassificationController.class)
//@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String SWAGGER_API_VERSION = "0.1";
    private static final String LICENCE_TEXT = "License";
    private static final String title = "Requirement Classifier API";
    private static final String description = "REST API for requirement classifier using mahout";

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .license(LICENCE_TEXT)
                .license(SWAGGER_API_VERSION)
                .build();
    }

    @Bean
    public Docket classifier_api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                /* Anything after upc will be included into my Swagger configuration */
                .paths(PathSelectors.any()/*PathSelectors.regex("/upc/classifier-component.*")*/)
                .build();
    }
}
