package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2)
              .apiInfo(getInfo())
              .select()
              .apis(RequestHandlerSelectors.any())
              .paths(PathSelectors.any())
              .build();
    }

    private ApiInfo getInfo() {
        return new ApiInfo("Blogging App Backend Documentation", "This is the documentation for the Blogging app", "1.0", "Terms of Service", new Contact("Nitish", "https://test.com" , "test@test.com"), "License of APIs", "License URL", Collections.emptyList());
    }
}
