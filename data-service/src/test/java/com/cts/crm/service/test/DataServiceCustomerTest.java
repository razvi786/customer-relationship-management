package com.cts.crm.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.cts.crm.exception.CustomerNotCreatedException;
import com.cts.crm.exception.CustomerNotFoundException;
import com.cts.crm.model.Customer;
import com.cts.crm.repo.CustomerJpaRepo;
import com.cts.crm.service.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class DataServiceCustomerTest {
	
	@InjectMocks
	CustomerServiceImpl customerService;
	
	@Mock
	CustomerJpaRepo customerRepo;
	
	@Test
	public void searchCustomerByIdTest() {
		Customer customer=new Customer(1, "Manu", 9878678990L, "manu@gmail.com", "Andhra Pradesh", null);
		when(customerRepo.findById(1)).thenReturn(Optional.ofNullable(customer));
		Customer createdCustomer = customerService.searchCustomerById(1);
		assertEquals("Manu", createdCustomer.getName());
		verify(customerRepo, times(1)).findById(1);
	}
	
	@Test
	public void searchCustomerByIdTestForException() {
		when(customerRepo.findById(1)).thenThrow(CustomerNotFoundException.class);
		assertThrows(CustomerNotFoundException.class, ()->{
			customerService.searchCustomerById(1);
		});
		verify(customerRepo,times(1)).findById(1);
	}
	
	@Test
	public void createCustomerTest() {
		when(customerRepo.save(Mockito.any(Customer.class))).thenAnswer(i -> i.getArgument(0));
		Customer customer = new Customer(1, "Manu", 9878678990L, "manu@gmail.com", "Andhra Pradesh", null);
		assertEquals(customer,customerService.createCustomer(customer));
		verify(customerRepo, times(1)).save(customer);
	}
	
	@Test
	public void createCustomerTestWithException() {
		when(customerRepo.save(Mockito.any(Customer.class))).thenThrow(CustomerNotCreatedException.class);
		Customer customer = new Customer(1, "Manu", 9878678990L, "manu@gmail.com", "Andhra Pradesh", null);
		assertThrows(CustomerNotCreatedException.class, ()->{
			customerService.createCustomer(customer);
		});
		verify(customerRepo,times(1)).save(customer);
	}

}
