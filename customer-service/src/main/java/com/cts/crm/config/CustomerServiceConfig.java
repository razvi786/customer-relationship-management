package com.cts.crm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import lombok.Data;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Data
@RefreshScope
public class CustomerServiceConfig {
	
	//Properties
	@Value("${spring.security.username}")
	private String username;
	
	@Value("${spring.security.password}")
	private String password;
	
	@Value("$spring.security.role}")
	private String[] roles;
	
	@Value("${rest.customer.get}")
	private String getCustomer;
	
	@Value("${rest.customer.post}")
	private String postCustomer;
	
	@Value("${rest.user.get}")
	private String getUser;
	
	@Value("${rest.user.post}")
	private String postUser;
	
	@Value("${rest.subscription.get}")
	private String getSubscription;
	
	@Value("${rest.subscription.post}")
	private String postSubscription;
	
	//Rest Template Configuration
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	//Swagger2 Configuration
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.cts.crm"))
				.build();
	}

}
