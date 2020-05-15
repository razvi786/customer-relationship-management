package com.cts.crm.config;

import java.util.List;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.cts.crm.model.Customer;
import com.cts.crm.model.Subscription;
import com.cts.crm.model.User;

@Component
public class RestTemplateUtil {
	
	@Autowired
	CustomerServiceConfig properties;
	
	public HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(properties.getUsername(), properties.getPassword());
		headers.set("Transaction-Id", MDC.get("transactionId"));
		return headers;
	}
	
	//Customer HttpEntity's
	public HttpEntity<Customer> getCustomerHttpEntity(){
		return new HttpEntity<>(getHeaders()); 
	}
	public HttpEntity<Customer> postCustomerHttpEntity(Customer customer){
		return new HttpEntity<>(customer,getHeaders()); 
	}
	
	//User HttpEntity's
	public HttpEntity<User> getUserHttpEntity(){
		return new HttpEntity<>(getHeaders()); 
	}
	public HttpEntity<User> postUserHttpEntity(User user){
		return new HttpEntity<>(user,getHeaders()); 
	}
	
	//Subscription HttpEntity's
	public HttpEntity<List<Subscription>> getSubscriptionHttpEntity(){
		return new HttpEntity<>(getHeaders()); 
	}
	public HttpEntity<Subscription> postSubscriptionHttpEntity(Subscription subscription){
		return new HttpEntity<>(subscription,getHeaders()); 
	}

}
