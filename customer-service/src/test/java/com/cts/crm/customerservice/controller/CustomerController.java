//package com.cts.crm.customerservice.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.net.URI;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.Assert;
//import org.springframework.web.client.RestTemplate;
//
//import com.cts.crm.CustomerServiceApplication;
//import com.cts.crm.controller.CustomerRestController;
//import com.cts.crm.exception.CustomerNotFoundException;
//import com.cts.crm.model.Customer;
//import com.cts.crm.service.rest.DataServiceRestTemplate;
//
//@SpringBootTest(classes = CustomerServiceApplication.class)
//public class CustomerController {
//	@InjectMocks
//	private CustomerRestController customercontroller;
//	@Mock
//	private DataServiceRestTemplate service;
//	@Autowired
//	RestTemplate restTemplate;
//	
//	@Test
//	public void searchCustomerByIdTest() {
//		when(service.searchCustomerById(1)).thenReturn(ResponseEntity.ok(new Customer(1, "manu", "9878678990", "m@gmail.com", "Allur", "admin")));
//
//		Customer customer = (Customer) customercontroller.searchCustomerById(1).getBody();
//
//		assertEquals("manu",customer.getName());
//		verify(service, times(1)).searchCustomerById(1);
//	}
//	@Test
//	public void searchCustomerByIdTestForException() {
//		Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
//			when(service.searchCustomerById(1)).thenThrow(CustomerNotFoundException.class);
//			customercontroller.searchCustomerById(1);
//		});
//
//		assertNotNull(exception);
//		verify(service, times(1)).searchCustomerById(1);
//	}
//	@Test
//	public void createCustomerTest()  {
//		when(service.createCustomer(Mockito.any(Customer.class))).thenAnswer(i -> i.getArgument(0));
//		Customer customer = new Customer(2, "manu1", "9878678990", "m@gmail.com", "Allur", "admin");
//		assertTrue(customer.equals(customercontroller.createCustomer(customer).getBody()));
//		verify(service, times(1)).createCustomer(Mockito.any(Customer.class));
//	}
//	/*@Test
//	public void createCustomerTest() 
//	{
//	    RestTemplate restTemplate = new RestTemplate();
//	     
//	    final String baseUrl = "http://localhost:" + "8001" + "/employees";
//	    URI uri = new URI(baseUrl);
//	 
//	    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
//	     
//	    //Verify request succeed
//	    Assert.assertEquals(200, result.getStatusCodeValue());
//	    Assert.assertEquals(true, result.getBody().contains("CustomerList"));
//	}*/
//	
//	
//	
//}
