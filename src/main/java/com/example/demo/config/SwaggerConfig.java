package com.example.demo.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${token.user.name}")
    private String tokenUserName;

    @Value("${refresh.user.name}")
    private String refreshUserName;

    @Value("${token.csrf.name}")
    private String tokenCsrfName;

    @Value("${project.title}")
    private String title;

    @Value("${project.version}")
    private String version;

    @Bean
    public Docket apiV1(){
        String version = this.version;
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(version))
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.
                        basePackage("com.example.demo.controller"))
                .paths(Predicates.or(PathSelectors.regex("^(?!/v2).+"))).build().
                        securitySchemes(apiKey());
    }
    /*
     * swagger에서는 header로 받고 싶으면 설정을 추가해줘야 한다.
     */
    private List<ApiKey> apiKey() {
        List<ApiKey> list = new ArrayList<>();
        list.add(new ApiKey("Bearer +accessToken", tokenUserName, "header"));
        list.add(new ApiKey("Bearer +refreshToken", refreshUserName, "header"));
        //list.add(new ApiKey(tokenCsrfName, tokenCsrfName, "header")); // 추후 추가 예정
        return list;
    }

    private ApiInfo apiInfo(String version) {
        return new ApiInfoBuilder()
                .title(title + " " +version)
                .description("API Doc for " + title)
                .version(version)
                .build();
    }
}
