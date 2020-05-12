package com.cts.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.cts.crm.exception.ServerDownException;
import com.cts.crm.model.Subscription;
import com.cts.crm.service.rest.DataServiceRestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@CrossOrigin(origins="*")
public class SubscriptionRestController {
	
	@Autowired
	DataServiceRestTemplate dataServiceRestTemplate;
	
	@GetMapping("customers/{customerId}/subscriptions/active")
	@HystrixCommand(fallbackMethod = "getFallbackSubscription",ignoreExceptions = HttpClientErrorException.class)
	public ResponseEntity<List<Subscription>> viewActiveSubscriptions(@PathVariable int customerId) {
		List<Subscription> subscriptions = dataServiceRestTemplate.viewActiveSubscriptions(customerId).getBody();
		return new ResponseEntity<List<Subscription>>(subscriptions,HttpStatus.OK);
	}
	
	@PostMapping("subscriptions")
	@HystrixCommand(fallbackMethod = "postFallbackSubscription",ignoreExceptions = HttpClientErrorException.class)
	public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
		Subscription createdSubscription = dataServiceRestTemplate.createSubscription(subscription).getBody();
		return new ResponseEntity<Subscription>(createdSubscription,HttpStatus.CREATED);
	}
	
	//	Hystrix Fallback Methods	
	public  ResponseEntity<List<Subscription>> getFallbackSubscription(@PathVariable int customerId){
		throw new ServerDownException("Server is currently DOWN! please try again later");
	}
	public  ResponseEntity<Subscription> postFallbackSubscription(@RequestBody Subscription subscription){
		throw new ServerDownException("Server is currently DOWN! please try again later");
	}
	
}
