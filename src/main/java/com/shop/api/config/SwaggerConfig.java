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

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
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

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("REST API Document")
	                .description("e-Shop Api")
	                .termsOfServiceUrl("localhost")
	                .version("3.0")
	                .build();
	    }

	    private ApiKey apiKey() {
	        return new ApiKey("token", "Authorization", "header"); //`apiKey` is the name of the APIKey, `Authorization` is the key in the request header
	    }
	    
//	    private SecurityContext securityContext() {
//			return SecurityContext.builder()
//					.securityReferences(defaultAuth())
//					.forPaths(PathSelectors.regex("/api/v1.*"))
//					.build();
//	    }
//	    
//	    private List<SecurityReference> defaultAuth() {
//	    	AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//	    	AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//	    	authorizationScopes[0] = authorizationScope;
//	    	return Arrays.asList(new SecurityReference("apiKey", authorizationScopes));
//	    }
//	
//	    @Bean
//	    public SecurityConfiguration security() {
//	    		return SecurityConfigurationBuilder.builder()
//	    				.clientId("test-app-client-id")
//	    				.clientSecret("test-app-client-secret")
//	    				.realm("test-app-realm")
//	    				.appName("test-app")
//	    				.scopeSeparator(",")
//	    				.additionalQueryStringParams(null)
//	    				.useBasicAuthenticationWithAccessCodeGrant(false)
//	    				.build();
//	    }
}