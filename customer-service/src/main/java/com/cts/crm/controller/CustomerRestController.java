package com.cts.crm.controller;

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

import com.cts.crm.config.TransactionUtil;
import com.cts.crm.exception.ServerDownException;
import com.cts.crm.model.Customer;
import com.cts.crm.service.rest.DataServiceRestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins="*")
@Slf4j
public class CustomerRestController {
	
	@Autowired
	DataServiceRestTemplate dataServiceRestTemplate;
	
	@Autowired
	TransactionUtil transactionUtil;
	
	@PostMapping("customers")
	@HystrixCommand(fallbackMethod ="postFallbackCustomer",ignoreExceptions = HttpClientErrorException.class)
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		transactionUtil.generateTransactionId("CREATE_CUSTOMER");
		log.info("Inside Create Customer [Customer Controller]");
		Customer createdCustomer = dataServiceRestTemplate.createCustomer(customer).getBody();
		return new ResponseEntity<>(createdCustomer,HttpStatus.CREATED);
	}
	
	@GetMapping("customers/{id}")
	@HystrixCommand(fallbackMethod ="getFallbackCustomer",ignoreExceptions = HttpClientErrorException.class)
	public ResponseEntity<Customer> searchCustomerById(@PathVariable Integer id) {
		transactionUtil.generateTransactionId("GET_CUSTOMER");
		log.info("Inside Search Customer By Id [Customer Controller]");
		Customer customer = dataServiceRestTemplate.searchCustomerById(id).getBody();
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
	//	Hystrix Fallback Methods	
	public  ResponseEntity<Customer> postFallbackCustomer(@RequestBody Customer customer){
		throw new ServerDownException("Server is currently DOWN! please try again later");
	}
	public  ResponseEntity<Customer> getFallbackCustomer(@PathVariable Integer id){
		throw new ServerDownException("Server is currently DOWN! please try again later");
	}
	

}
