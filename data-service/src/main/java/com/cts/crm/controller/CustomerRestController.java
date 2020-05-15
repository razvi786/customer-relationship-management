package com.cts.crm.controller;

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
import com.cts.crm.exception.CustomerNotFoundException;
import com.cts.crm.model.Customer;
import com.cts.crm.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerRestController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	TransactionUtil transactionUtil;
	
	@PostMapping("customers")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer,@RequestHeader HttpHeaders headers) {
		transactionUtil.setTransactionId(headers);
		log.info("Inside Create Customer [Customer Controller]");
		Customer createdCustomer = customerService.createCustomer(customer);
		return new ResponseEntity<>(createdCustomer,HttpStatus.CREATED);
	}
	
	@GetMapping("customers/{id}")
	public ResponseEntity<Customer> searchCustomerById(@PathVariable int id,@RequestHeader HttpHeaders headers) {
		transactionUtil.setTransactionId(headers);
		log.info("Inside Search Customer By Id [Customer Controller]");
		Customer customer = customerService.searchCustomerById(id);
		if(customer==null)
			throw new CustomerNotFoundException("id: "+id+" not found");
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}

}
