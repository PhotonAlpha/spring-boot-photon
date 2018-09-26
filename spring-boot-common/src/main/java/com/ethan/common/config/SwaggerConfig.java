/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.common.config;

import com.google.common.base.Predicates;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.SecurityConfig;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-26 09:48
 **/
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "swagger2", value = {"enable"}, havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ethan.common.controller"))
                .paths(Predicates.not(PathSelectors.regex("/api*")))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Cao Qiang", "", "411084090@qq.com");
        return new ApiInfoBuilder()
                .title("Spring Boot Swagger")
                .description("Spring Boot Project")
                .version("1.0.0")
                .license("Apache 2.0")
                .contact(contact)
                .build();
    }


}
