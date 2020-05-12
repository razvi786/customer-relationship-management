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

@Configuration
public class DataServiceRestTemplate {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CustomerServiceConfig properties;
	
	@Autowired
	RestTemplateUtil restTemplateUtil;
	
	//Customer Rest Controller Methods	
	public ResponseEntity<Customer> createCustomer(Customer customer) {
		return restTemplate.exchange(properties.getPostCustomer(), HttpMethod.POST,
				restTemplateUtil.postCustomerHttpEntity(customer), Customer.class);
	}

	public ResponseEntity<Customer> searchCustomerById(Integer id) {
		return restTemplate.exchange(properties.getGetCustomer(), HttpMethod.GET,
				restTemplateUtil.getCustomerHttpEntity(),
				Customer.class, new HashMap<String,Integer>(){
					private static final long serialVersionUID = 5488567930634984918L;
					{
						put("id",id);
					}
				});
	}
	
	//User Rest Controller Methods
	
	public ResponseEntity<User> createUser(User user) {
		return restTemplate.exchange(properties.getPostUser(), HttpMethod.POST,
				restTemplateUtil.postUserHttpEntity(user), User.class);
	}

	public ResponseEntity<User> getUserByEmailAndPassword(String email, String password) {
		return restTemplate.exchange(properties.getGetUser(), HttpMethod.GET,
				restTemplateUtil.getUserHttpEntity(), User.class,
				new HashMap<String,String>(){
					private static final long serialVersionUID = 4193806209826745125L;
					{
						put("email", email);
						put("password",password);
					}
				});
	}
	
	//Subscription Rest Controller Methods
	
	public ResponseEntity<Subscription> createSubscription(Subscription subscription) {
		return restTemplate.exchange(properties.getPostSubscription(), HttpMethod.POST,
				restTemplateUtil.postSubscriptionHttpEntity(subscription), Subscription.class);
	}
	
	public ResponseEntity<List<Subscription>> viewActiveSubscriptions(int customerId) {
		return restTemplate.exchange(properties.getGetSubscription(), HttpMethod.GET,
				restTemplateUtil.getSubscriptionHttpEntity(),
				new ParameterizedTypeReference<List<Subscription>>(){},
				new HashMap<String,Integer>() {
					private static final long serialVersionUID = -575731509652938109L;
					{
						put("customerId",customerId);
					}
				});
	}

}
