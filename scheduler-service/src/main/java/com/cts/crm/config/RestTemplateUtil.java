package com.cts.crm.config;

import java.util.List;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.cts.crm.model.Subscription;

@Component
public class RestTemplateUtil {
	
	@Autowired
	SchedulerServiceConfig properties;
	
	public HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(properties.getUsername(), properties.getPassword());
		headers.set("Transaction-Id", MDC.get("transactionId"));
		return headers;
	}
	
	//Subscription HttpEntity's
	public HttpEntity<List<Subscription>> getSubscriptionHttpEntity(){
		return new HttpEntity<>(getHeaders());
	}
	public HttpEntity<List<Subscription>> inactiveSubscriptionHttpEntity(List<Subscription> subscriptions){
		return new HttpEntity<>(subscriptions,getHeaders());
	}

}
