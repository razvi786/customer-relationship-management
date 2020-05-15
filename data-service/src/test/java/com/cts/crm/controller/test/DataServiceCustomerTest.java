//package com.cts.crm.controller.test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import com.cts.crm.DataServiceApplication;
//import com.cts.crm.controller.CustomerRestController;
//import com.cts.crm.exception.CustomerNotFoundException;
//import com.cts.crm.model.Customer;
//import com.cts.crm.service.CustomerService;
//
//@SpringBootTest(classes = DataServiceApplication.class)
//public class DataServiceCustomerTest {
//	@Mock
//	private CustomerService customerservice;
//	@InjectMocks
//	private CustomerRestController customercontroller;
//	
//	@Test
//	public void searchCustomerByIdTest(){
//		when(customerservice.searchCustomerById(1)).thenReturn(new Customer(1, "manu", "9878678990", "m@gmail.com", "Allur", "admin"));
//		Customer customer = (Customer) customercontroller.searchCustomerById(1).getBody();
//		assertEquals("manu", customer.getName());
//		verify(customerservice, times(1)).searchCustomerById(1);
//	}
//	@Test
//	public void searchCustomerByIdTestForException() {
//		Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
//			when(customerservice.searchCustomerById(1)).thenThrow(CustomerNotFoundException.class);
//			customercontroller.searchCustomerById(1);});
//
//		assertNotNull(exception);
//		verify(customerservice, times(1)).searchCustomerById(1);
//	}
//	@Test
//	public void createCustomerTest() {
//		when(customerservice.createCustomer(Mockito.any(Customer.class))).thenAnswer(i -> i.getArgument(0));
//		Customer customer = new Customer(1, "Ramu","9080807866", "r@gmail.com", "ALU", "admin");
//		assertTrue(customer.equals(customercontroller.createCustomer(customer).getBody()));
//		verify(customerservice, times(1)).createCustomer(Mockito.any(Customer.class));
//	}
//	
//
//}
