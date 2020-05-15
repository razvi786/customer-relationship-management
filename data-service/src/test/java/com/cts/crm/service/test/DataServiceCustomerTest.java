package com.cts.crm.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.crm.DataServiceApplication;
import com.cts.crm.exception.CustomerNotFoundException;
import com.cts.crm.model.Customer;
import com.cts.crm.service.CustomerService;
import com.cts.crm.service.CustomerServiceImpl;

@SpringBootTest(classes = DataServiceApplication.class)
public class DataServiceCustomerTest {
	
	@InjectMocks
	CustomerService customerService = new CustomerServiceImpl();
	
	@Mock
	CustomerJpaRepo customerRepo;
	
	@Test
	public void searchCustomerByIdTest() {
		when(customerRepo.findById(1)).thenReturn(Optional.of(new Customer(1, "manu", "9878678990", "m@gmail.com", "Allur", "admin")));

		Customer customer = customerService.searchCustomerById(1);

		assertEquals("manu", customer.getName());
		verify(customerRepo, times(1)).findById(1);
	}
	@Test
	public void searchCustomerByIdTestForException() {
		Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
			when(customerRepo.findById(1)).thenThrow(CustomerNotFoundException.class);
			customerService.searchCustomerById(1);
		});

		assertNotNull(exception);
		verify(customerRepo, times(1)).findById(1);
	}
	@Test
	public void createCustomerTest() {
		when(customerRepo.save(Mockito.any(Customer.class))).thenAnswer(i -> i.getArgument(0));
		
		Customer customer = new Customer(1, "manu", "9878678990", "m@gmail.com", "Allur", "admin");
		
		assertTrue(customer.equals(customerService.createCustomer(customer)));
		verify(customerRepo, times(1)).save(Mockito.any(Customer.class));
	}

}
