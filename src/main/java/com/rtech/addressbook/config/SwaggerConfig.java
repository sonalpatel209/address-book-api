package com.rtech.addressbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    Docket api() {

        RequestParameter xRequestIdHeader = new RequestParameterBuilder()
                .name("x-request-id")
                .description("Unique Request ID")
                .required(true)
                .in(ParameterType.HEADER)
                .build();

        RequestParameter xRequestedByHeader = new RequestParameterBuilder()
                .name("x-client-id")
                .description("Requested By information")
                .required(true)
                .in(ParameterType.HEADER)
                .build();
        List<RequestParameter> globalParams = new ArrayList<>();
        globalParams.add(xRequestIdHeader);
        globalParams.add(xRequestedByHeader);
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/rtech*/**"))
                .build()
                .apiInfo(apiInfo())
                .globalRequestParameters(globalParams);

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Address Book API")
                .description("<p>These APIs consolidate information from Address Book.  "
                        + "This information is intended to be consumed by backend end clients"
                        + "<p>Key 'x-client-id' and 'x-request-id' must be present in the Request Headers.</p>"
                        + "<p><b>Version 1</b> endpoints were the initial implementation deployed in 2021.<br>")
                .version("v1")
                .termsOfServiceUrl("")
                .contact(new Contact("Sonal Patel", "", "sonal.patel209@gmail.com"))
                .license("")
                .licenseUrl("")
                .build();


    }
}
