package com.vij.football.configuration;

import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket postsApi() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("api/internal").apiInfo(apiInfo()).select().paths(
        postPaths()).build();
  }

  private Predicate<String> postPaths() {
    return regex("/api/service/.*");
  }

  @SuppressWarnings("deprecation")
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Football League Api").description("Football League Api").contact("vijendra.bhat@outlook.com").license("JavaInUse License").licenseUrl(
            "vijendra.bhat@outlook.com").version("1.0").build();
  }
}
