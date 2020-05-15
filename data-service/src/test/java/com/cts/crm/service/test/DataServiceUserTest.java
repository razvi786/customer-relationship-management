//package com.cts.crm.service.test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.cts.crm.DataServiceApplication;
//import com.cts.crm.model.User;
//import com.cts.crm.repo.UserJdbcRepo;
//import com.cts.crm.repo.UserJpaRepo;
//import com.cts.crm.service.UserService;
//import com.cts.crm.service.UserServiceImpl;
//
//@SpringBootTest(classes = DataServiceApplication.class)
//public class DataServiceUserTest {
//	
//	@InjectMocks
//	UserService userService = new UserServiceImpl();
//	
//	@Mock
//	UserJpaRepo userRepo;
//	
//	@Test
//	public void createUserTest(){
//		when(userRepo.save(Mockito.any(User.class))).thenAnswer(i -> i.getArgument(0));
//		User user = new User(7, "Ramu", "rm@gmail.com", "ramu123", "user");
//		assertTrue(user.equals(userService.createUser(user)));
//		verify(userRepo, times(1)).save(Mockito.any(User.class));
//	}
//	
//	@Test
//	public void getUserByEmailAndPasswordTest() {
//		when(userRepo.findByEmailAndPassword("r@gmail.com", "ramu123")).thenReturn((new User(1, "Ramu", "r@gmail.com", "ramu123", "user")));
//
//		User user = userService.getUserByEmailAndPassword("r@gmail.com", "ramu123");
//		//assertEquals("Lokesh", patient.getName());
//		verify(userRepo, times(1)).findByEmailAndPassword("r@gmail.com", "ramu123");
//	}
//
//
//	
//
//
//}
