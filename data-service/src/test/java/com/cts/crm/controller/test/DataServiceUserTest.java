//package com.cts.crm.controller.test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.cts.crm.DataServiceApplication;
//import com.cts.crm.controller.CustomerRestController;
//import com.cts.crm.controller.UserRestController;
//import com.cts.crm.model.User;
//import com.cts.crm.service.UserService;
//
//@SpringBootTest(classes = DataServiceApplication.class)
//public class DataServiceUserTest {
//	@Mock
//	private UserService userservice;
//	@InjectMocks
//	private UserRestController usercontroller;
//	@Test
//	public void createUserTest() {
//		when(userservice.createUser(Mockito.any(User.class))).thenAnswer(i -> i.getArgument(0));
//		User user = new User(1, "Ramu", "r@gmail.com", "ramu123", "user");
//		assertTrue(user.equals(usercontroller.createUser(user).getBody()));
//		verify(userservice, times(1)).createUser(Mockito.any(User.class));
//	}
//	@Test
//	public void getUserByEmailAndPasswordTest()
//	{
//		when(userservice.getUserByEmailAndPassword("r@gmail.com", "ramu123")).thenReturn(new User(1, "Ramu", "r@gmail.com", "ramu123", "user"));
//		User user=(User) usercontroller.getUserByEmailAndPassword("r@gmail.com", "ramu123").getBody();
//		assertEquals("r@gmail.com",user.getEmail());
//		verify(userservice, times(1)).getUserByEmailAndPassword("r@gmail.com", "ramu123");
//	}
//	
//	
//	
//
//}
