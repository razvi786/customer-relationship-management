package com.cts.crm.config;

import java.util.List;

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
		return headers;
	}
	
	//Subscription HttpEntity's
	public HttpEntity<List<Subscription>> getSubscriptionHttpEntity(){
		return new HttpEntity<List<Subscription>>(getHeaders());
	}
	public HttpEntity<List<Subscription>> inactiveSubscriptionHttpEntity(List<Subscription> subscriptions){
		return new HttpEntity<List<Subscription>>(subscriptions,getHeaders());
	}

}
