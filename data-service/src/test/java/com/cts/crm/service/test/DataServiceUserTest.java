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

import com.cts.crm.exception.UserNotCreatedException;
import com.cts.crm.exception.UserNotFoundException;
import com.cts.crm.model.User;
import com.cts.crm.repo.UserJpaRepo;
import com.cts.crm.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class DataServiceUserTest {
	
	@InjectMocks
	UserServiceImpl userService;
	
	@Mock
	UserJpaRepo userRepo;
	
	@Test
	public void getUserByEmailAndPasswordTest() {
		User user = new User(1, "Ramu", "ramu@gmail.com", "ramu123", "ROLE_USER");
		when(userRepo.findByEmailAndPassword("ramu@gmail.com", "ramu123")).thenReturn(Optional.ofNullable(user));
		User createdUser = userService.getUserByEmailAndPassword("ramu@gmail.com", "ramu123");
		assertEquals("Ramu", createdUser.getName());
		verify(userRepo, times(1)).findByEmailAndPassword("ramu@gmail.com", "ramu123");
	}
	
	@Test
	public void getUserByEmailAndPasswordTestForException() {
		when(userRepo.findByEmailAndPassword(Mockito.anyString(),Mockito.anyString())).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, ()->{
			userService.getUserByEmailAndPassword("ramu@gmail.com", "ramu123");
		});
		verify(userRepo,times(1)).findByEmailAndPassword("ramu@gmail.com", "ramu123");
	}

	@Test
	public void createUserTest(){
		when(userRepo.save(Mockito.any(User.class))).thenAnswer(i -> i.getArgument(0));
		User user = new User(2, "Ramu", "ramu@gmail.com", "ramu123", "ROLE_USER");
		assertEquals(user,userService.createUser(user));
		verify(userRepo, times(1)).save(user);
	}
	
	@Test
	public void createUserTestForException(){
		when(userRepo.save(Mockito.any(User.class))).thenThrow(UserNotCreatedException.class);
		User user = new User(2, "Ramu", "ramu@gmail.com", "ramu123", "ROLE_USER");
		assertThrows(UserNotCreatedException.class, ()->{
			userService.createUser(user);
		});
		verify(userRepo,times(1)).save(user);
	}

}
