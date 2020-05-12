package com.cts.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.crm.model.Subscription;
import com.cts.crm.service.SubscriptionService;

@RestController
public class SubscriptionRestController {
	
	@Autowired
	SubscriptionService subscriptionService;
	
	@GetMapping("customers/{customerId}/subscriptions/active")
	public ResponseEntity<List<Subscription>> viewActiveSubscriptions(@PathVariable int customerId){
		List<Subscription> subscriptions = subscriptionService.viewActiveSubscriptions(customerId);
		return new ResponseEntity<List<Subscription>>(subscriptions,HttpStatus.OK);
	}
	
	@PostMapping("subscriptions/batch-inactive")
	public ResponseEntity<String> batchInactiveSubscriptions(@RequestBody List<Subscription> subscriptions) {
		subscriptionService.batchInactiveSubscription(subscriptions);
		return new ResponseEntity<String>("Today's Subscriptions are now Inactive.", HttpStatus.OK);
	}
	
	@GetMapping("subscriptions")
	public ResponseEntity<List<Subscription>> getAllSubscriptions(){
		List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
		return new ResponseEntity<List<Subscription>>(subscriptions,HttpStatus.OK);
	}
	
	@PostMapping("subscriptions")
	public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
		Subscription createdSubscription = subscriptionService.createSubscription(subscription);
		return new ResponseEntity<Subscription>(createdSubscription,HttpStatus.CREATED);
	}
	
	@GetMapping("subscriptions/today")
	public ResponseEntity<List<Subscription>> getAllSubscriptionsOfToday(){
		List<Subscription> subscriptions = subscriptionService.getAllSubscriptionsOfToday();
		return new ResponseEntity<List<Subscription>>(subscriptions, HttpStatus.OK);
	}
	
}
