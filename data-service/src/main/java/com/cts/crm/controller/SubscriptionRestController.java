package com.cts.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.crm.config.TransactionUtil;
import com.cts.crm.model.Subscription;
import com.cts.crm.service.SubscriptionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SubscriptionRestController {
	
	@Autowired
	SubscriptionService subscriptionService;
	
	@Autowired
	TransactionUtil transactionUtil;
	
	@GetMapping("customers/{customerId}/subscriptions/active")
	public ResponseEntity<List<Subscription>> viewActiveSubscriptions(@PathVariable int customerId,@RequestHeader HttpHeaders headers){
		transactionUtil.setTransactionId(headers);
		log.info("Inside View Active Subscriptions [Subscription Controller]");
		List<Subscription> subscriptions = subscriptionService.viewActiveSubscriptions(customerId);
		return new ResponseEntity<>(subscriptions,HttpStatus.OK);
	}
	
	@PostMapping("subscriptions/batch-inactive")
	public ResponseEntity<String> batchInactiveSubscriptions(@RequestBody List<Subscription> subscriptions,@RequestHeader HttpHeaders headers) {
		transactionUtil.setTransactionId(headers);
		log.info("Inside Batch Inactive Subscriptions [Subscription Controller]");
		String message = subscriptionService.batchInactiveSubscription(subscriptions);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@GetMapping("subscriptions")
	public ResponseEntity<List<Subscription>> getAllSubscriptions(@RequestHeader HttpHeaders headers){
		transactionUtil.setTransactionId(headers);
		log.info("Inside Get All Subscriptions [Subscription Controller]");
		List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
		return new ResponseEntity<>(subscriptions,HttpStatus.OK);
	}
	
	@PostMapping("subscriptions")
	public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription,@RequestHeader HttpHeaders headers) {
		transactionUtil.setTransactionId(headers);
		log.info("Inside Create Subscription [Subscription Controller]");
		Subscription createdSubscription = subscriptionService.createSubscription(subscription);
		return new ResponseEntity<>(createdSubscription,HttpStatus.CREATED);
	}
	
	@GetMapping("subscriptions/today")
	public ResponseEntity<List<Subscription>> getAllSubscriptionsOfToday(@RequestHeader HttpHeaders headers){
		transactionUtil.setTransactionId(headers);
		log.info("Inside Get All Subscriptions of Today [Subscription Controller]");
		List<Subscription> subscriptions = subscriptionService.getAllSubscriptionsOfToday();
		return new ResponseEntity<>(subscriptions, HttpStatus.OK);
	}
	
}
