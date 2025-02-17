package com.zzx.graduation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zzx.graduation.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //swagger原文参考地址链接：http://blog.didispace.com/springbootswagger2/
    //访问swagger2api地址http://localhost:8080/swagger-ui.html
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("swagger原文参考地址链接：http://blog.didispace.com/springbootswagger2/")
                .termsOfServiceUrl("http://blog.didispace.com/")
                .contact("zhouzixin")
                .version("1.0")
                .build();
    }

}
