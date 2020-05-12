package com.cts.crm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Data
@RefreshScope
public class DataServiceConfig {
	
	//Properties	
	@Value("${spring.security.username}")
	private String username;
	
	@Value("${spring.security.password}")
	private String password;
	
	@Value("$spring.security.role}")
	private String[] roles;
	
	@Value("${queries.jpa.enable}")
	private String jpaEnable;
	
	@Value("${query.customer.find-by-id}")
	private String customerFindById;
	
	@Value("${query.customer.save}")
	private String customerSave;
	
	@Value("${query.user.find-by-id}")
	private String userFindById;
	
	@Value("${query.user.find-by-email-and-password}")
	private String userFindByEmailAndPassword;
	
	@Value("${query.user.save}")
	private String userSave;
	
	@Value("${query.subscription.find-by-id}")
	private String subscriptionFindById;
	
	@Value("${query.subscription.save}")
	private String subscriptionSave;
	
	@Value("${query.subscription.find-all}")
	private String subscriptionFindAll;
	
	@Value("${query.subscription.view-active-subscriptions}")
	private String subscriptionViewActiveSubscriptions;
	
	@Value("${query.subscription.batch-inactive-subscription}")
	private String subscriptionBatchInactiveSubscription;
	
	//Swagger2 Configurations
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.cts.crm"))
				.build();
	}

}
