package com.cts.crm.service;

import com.cts.crm.model.Customer;

public interface CustomerService {
	
	public Customer createCustomer(Customer customer);
	
	public Customer searchCustomerById(int id);

}
