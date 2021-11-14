package io.minio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Knife4jConfiguration.
 *
 * @author andy
 * @date 2021/11/14 8:54
 */
@Configuration
@EnableSwagger2
public class Knife4jConfiguration {
    @Value("${application.swagger.title}")
    private String apiTitle;
    @Value("${application.swagger.description}")
    private String apiDescription;
    @Value("${application.swagger.version}")
    private String apiVersion;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.minio"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo())
                .produces(Collections.singleton(MediaType.APPLICATION_JSON_VALUE))
                .consumes(Collections.singleton(MediaType.APPLICATION_JSON_VALUE));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(apiTitle)
                .version(apiVersion)
                .description(apiDescription)
                .build();
    }
//extends WebMvcConfigurationSupport
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
}
