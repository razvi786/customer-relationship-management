package com.cts.crm.service.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.cts.crm.config.RestTemplateUtil;
import com.cts.crm.config.SchedulerServiceConfig;
import com.cts.crm.config.TransactionUtil;
import com.cts.crm.model.Subscription;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DataServiceRestTemplate {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	RestTemplateUtil restTemplateUtil;
	
	@Autowired
	SchedulerServiceConfig properties;
	
	@Autowired
	TransactionUtil transactionUtil;
	
	//Subscription Rest Controller Methods
	public ResponseEntity<List<Subscription>> getAllSubscriptions() {
		transactionUtil.generateTransactionId("GET_SUBSCRIPTION");
		log.info("Inside Get All Subscriptions [Rest Template]");
		return restTemplate.exchange(properties.getGetSubscription(), HttpMethod.GET,
				restTemplateUtil.getSubscriptionHttpEntity(),
				new ParameterizedTypeReference<List<Subscription>>(){});
	}

	public ResponseEntity<String> batchInactiveSubscriptions(List<Subscription> todaySubscriptions) {
		transactionUtil.generateTransactionId("INACTIVE_SUBSCRIPTION");
		log.info("Inside Batch Inactive Subscriptions [Rest Template]");
		return restTemplate.exchange(properties.getInactiveSubscription(), HttpMethod.POST,
				restTemplateUtil.inactiveSubscriptionHttpEntity(todaySubscriptions), String.class);
	}

}
