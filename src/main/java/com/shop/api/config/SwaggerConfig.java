package com.shop.api.config;

import java.util.Arrays;
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

/**
 * The Class SwaggerConfig.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	 /**
 	 * Api.
 	 *
 	 * @return the docket
 	 */
 	@Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.any())
	                .paths(PathSelectors.any())
	                .build()
	                .apiInfo(apiInfo())
//	                .securityContexts(Arrays.asList(securityContext()))
	                .securitySchemes(Arrays.asList(apiKey()));
	    }

	    /**
    	 * Api info.
    	 *
    	 * @return the api info
    	 */
    	private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("REST API Document")
	                .description("e-Shop Api")
	                .termsOfServiceUrl("localhost")
	                .version("3.0")
	                .build();
	    }

	    /**
    	 * Api key.
    	 *
    	 * @return the api key
    	 */
    	private ApiKey apiKey() {
	        return new ApiKey("token", "Authorization", "header");
	    }
}