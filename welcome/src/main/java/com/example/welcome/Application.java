package com.example.welcome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    /**
     * This is api method.
     * 
     * @return Docket
     */
    @Bean
    public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2).select()
          .apis(RequestHandlerSelectors
              .basePackage("com.example.welcome.controller"))
          .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
      return new ApiInfoBuilder().title("Welcome Service")
          .description("Welcome services description").version("2.0").build();
    }   
}
