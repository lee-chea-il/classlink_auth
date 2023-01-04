package com.classlink.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
public class SwaggerConfig {
	private ApiInfo apiInfo() {		
		StringBuffer desc = new StringBuffer();
		desc.append("토큰발급 및 로그인 관련해서 아래의 링크를 참고해 주세요. <br>");
		desc.append("<a href = 'https://docs.google.com/spreadsheets/d/1V56Nsr2Q5U0BGFeJMAZkzp4cj3kyeaQXqweEO3xHaTk/edit?usp=sharing' target='_blank'>토큰발급 관련정보</a>");
		return new ApiInfoBuilder()
						.title("ClassLink Auth API List")
						.description(desc.toString())
						.termsOfServiceUrl("")
						.license("")
						.licenseUrl("")
						.version("1.0.0").build();
	}
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.classlink.auth.controller"))
				.paths(PathSelectors.any()).build();
	}
	

	/**
	 * SwaggerUI information
	 */
	@Bean
	UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder()
						.deepLinking(true)
						.displayOperationId(false)
						.defaultModelsExpandDepth(1)
						.defaultModelExpandDepth(1)
						.defaultModelRendering(ModelRendering.EXAMPLE)
						.displayRequestDuration(false)
						.docExpansion(DocExpansion.NONE)
						.filter(false)
						.maxDisplayedTags(null)
						.operationsSorter(OperationsSorter.ALPHA)
						.showExtensions(false)
						.tagsSorter(TagsSorter.ALPHA)
						.validatorUrl(null)
						.build();
	}
	
}