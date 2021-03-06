package com.cts.crm.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.crm.config.DataServiceConfig;
import com.cts.crm.model.Customer;
import com.cts.crm.repo.CustomerJdbcRepo;
import com.cts.crm.repo.CustomerJpaRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerJpaRepo customerJpaRepo;
	
	@Autowired
	CustomerJdbcRepo customerJdbcRepo;
	
	@Autowired
	DataServiceConfig properties;

	@Override
	public Customer createCustomer(Customer customer) {
		log.info("Inside Create Customer [Customer Service]");
		return customerJpaRepo.save(customer);
//		if(properties.getJpaEnable().equalsIgnoreCase("Y"))
//			return customerJpaRepo.save(customer);
//		else
//			return customerJdbcRepo.save(customer);
	}

	@Override
	public Customer searchCustomerById(int id) {
		log.info("Inside Search Customer By Id [Customer Service]");
		return customerJpaRepo.findById(id).orElse(null);
//		if(properties.getJpaEnable().equalsIgnoreCase("Y"))
//			return customerJpaRepo.findById(id).orElse(null);
//		else
//			return customerJdbcRepo.findById(id).orElse(null);		
	}

}
