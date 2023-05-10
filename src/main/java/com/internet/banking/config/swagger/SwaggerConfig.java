package com.internet.banking.config.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.PathProvider;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private ServletContext servletContext;
    @Value("${swagger.host}")
    private String swaggerHost;

    @Value("${swagger.base.path}")
    private String swaggerBaseRef;

    @Value("${swagger.base.package}")
    private String basePackage;

    @Value("${swagger.api.title}")
    private String apiTile;

    @Value("${swagger.api.description}")
    private String apiDescription;

    @Value("${swagger.api.version}")
    private String apiVersion;

    @Value("${swagger.api.contact.name}")
    private String apiName;

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerHost)
                .pathProvider(getPathProvider())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo());
    }

    private PathProvider getPathProvider()  {
        return new Rpp(servletContext);
    }

    private ApiInfo apiInfo()  {
        return new ApiInfo(
                apiTile,
                apiDescription.concat(", " + swaggerHost),
                apiVersion,
                "***",
                new Contact(apiName, "***", "***"), null, null,
                Collections.emptyList()
        );
    }


    private class Rpp extends RelativePathProvider {
        public Rpp(ServletContext servletContext) {
            super(servletContext);
        }

        @Override
        public String getApplicationBasePath() {
            return swaggerBaseRef;
        }
    }
}
