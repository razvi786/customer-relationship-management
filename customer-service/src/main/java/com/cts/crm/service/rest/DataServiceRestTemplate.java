package com.cts.crm.service.rest;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.cts.crm.config.CustomerServiceConfig;
import com.cts.crm.config.RestTemplateUtil;
import com.cts.crm.model.Customer;
import com.cts.crm.model.Subscription;
import com.cts.crm.model.User;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DataServiceRestTemplate {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CustomerServiceConfig properties;
	
	@Autowired
	RestTemplateUtil restTemplateUtil;
	
	//Customer Rest Controller Methods	
	public ResponseEntity<Customer> createCustomer(Customer customer) {
		log.info("Inside Create Customer [Rest Template]");
		return restTemplate.exchange(properties.getPostCustomer(), HttpMethod.POST,
				restTemplateUtil.postCustomerHttpEntity(customer), Customer.class);
	}

	public ResponseEntity<Customer> searchCustomerById(Integer id) {
		log.info("Inside Search Customer By Id [Rest Template]");
		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("id", id);
		return restTemplate.exchange(properties.getGetCustomer(), HttpMethod.GET,
				restTemplateUtil.getCustomerHttpEntity(), Customer.class, uriVariables);
	}
	
	//User Rest Controller Methods
	
	public ResponseEntity<User> createUser(User user) {
		log.info("Inside Create User [Rest Template]");
		return restTemplate.exchange(properties.getPostUser(), HttpMethod.POST,
				restTemplateUtil.postUserHttpEntity(user), User.class);
	}

	public ResponseEntity<User> getUserByEmailAndPassword(String email, String password) {
		log.info("Inside Get User By Email and Password [Rest Template]");
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("email", email);
		uriVariables.put("password", password);
		return restTemplate.exchange(properties.getGetUser(), HttpMethod.GET,
				restTemplateUtil.getUserHttpEntity(), User.class, uriVariables);
	}
	
	//Subscription Rest Controller Methods
	
	public ResponseEntity<Subscription> createSubscription(Subscription subscription) {
		log.info("Inside Create Subscription [Rest Template]");
		return restTemplate.exchange(properties.getPostSubscription(), HttpMethod.POST,
				restTemplateUtil.postSubscriptionHttpEntity(subscription), Subscription.class);
	}
	
	public ResponseEntity<List<Subscription>> viewActiveSubscriptions(int customerId) {
		log.info("Inside View Active Subscriptions [Rest Template]");
		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("customerId", customerId);
		return restTemplate.exchange(properties.getGetSubscription(), HttpMethod.GET,
				restTemplateUtil.getSubscriptionHttpEntity(),
				new ParameterizedTypeReference<List<Subscription>>(){}, uriVariables);
	}

}
