package com.cts.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.crm.config.DataServiceConfig;
import com.cts.crm.model.Customer;
import com.cts.crm.repo.CustomerJdbcRepo;
import com.cts.crm.repo.CustomerJpaRepo;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerJpaRepo customerJpaRepo;
	
	@Autowired
	CustomerJdbcRepo customerJdbcRepo;
	
	@Autowired
	DataServiceConfig properties;

	@Override
	public Customer createCustomer(Customer customer) {
		if(properties.getJpaEnable().equalsIgnoreCase("Y"))
			return customerJpaRepo.save(customer);
		else
			return customerJdbcRepo.save(customer);
	}

	@Override
	public Customer searchCustomerById(int id) {
		if(properties.getJpaEnable().equalsIgnoreCase("Y"))
			return customerJpaRepo.findById(id).orElse(null);
		else
			return customerJdbcRepo.findById(id).orElse(null);		
	}

}
