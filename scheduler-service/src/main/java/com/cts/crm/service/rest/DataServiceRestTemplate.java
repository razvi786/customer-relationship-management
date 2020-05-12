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
import com.cts.crm.model.Subscription;

@Configuration
public class DataServiceRestTemplate {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	RestTemplateUtil restTemplateUtil;
	
	@Autowired
	SchedulerServiceConfig properties;
	
	//Subscription Rest Controller Methods
	public ResponseEntity<List<Subscription>> getAllSubscriptions() {
		return restTemplate.exchange(properties.getGetSubscription(), HttpMethod.GET,
				restTemplateUtil.getSubscriptionHttpEntity(),
				new ParameterizedTypeReference<List<Subscription>>(){});
	}

	public ResponseEntity<String> batchInactiveSubscriptions(List<Subscription> todaySubscriptions) {
		return restTemplate.exchange(properties.getInactiveSubscription(), HttpMethod.POST,
				restTemplateUtil.inactiveSubscriptionHttpEntity(todaySubscriptions), String.class);
	}

}
