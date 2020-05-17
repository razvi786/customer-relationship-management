package com.cts.crm.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.cts.crm.exception.SubscriptionException;
import com.cts.crm.exception.SubscriptionNotCreatedException;
import com.cts.crm.exception.SubscriptionNotFoundException;
import com.cts.crm.model.Customer;
import com.cts.crm.model.Subscription;
import com.cts.crm.repo.SubscriptionJdbcRepo;
import com.cts.crm.repo.SubscriptionJpaRepo;
import com.cts.crm.service.SubscriptionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class DataServiceSubscriptionTest {
	
	@Mock
	SubscriptionJpaRepo subscriptionRepo;
	
	@Mock
	SubscriptionJdbcRepo subscriptionJdbcRepo;
	
	@InjectMocks
	SubscriptionServiceImpl subscriptionService;
	
	@Test
	public void getSubscriptionByIdTest() {
		Subscription subscription = new Subscription(3, "1 Month Subscription", Date.valueOf(LocalDate.now()), true, new Customer(1));
		when(subscriptionRepo.findById(3)).thenReturn(Optional.ofNullable(subscription));
		Subscription createdSubscription = subscriptionService.getSubscriptionById(3);
		assertEquals("1 Month Subscription", createdSubscription.getName());
		verify(subscriptionRepo,times(1)).findById(3);
	}
	
	@Test
	public void getSubscriptionByIdTestForException() {
		when(subscriptionRepo.findById(3)).thenThrow(SubscriptionNotFoundException.class);
		assertThrows(SubscriptionNotFoundException.class, ()->subscriptionService.getSubscriptionById(3));
		verify(subscriptionRepo,times(1)).findById(3);
	}
	
	@Test
	public void createSubscriptionTest() {
		when(subscriptionRepo.save(Mockito.any(Subscription.class))).thenAnswer(i->i.getArgument(0));
		Subscription subscription = new Subscription(3, "1 Month Subscription", Date.valueOf(LocalDate.now()), true, new Customer(1));
		assertEquals(subscription, subscriptionService.createSubscription(subscription));
		verify(subscriptionRepo,times(1)).save(subscription);
	}
	
	@Test
	public void createSubscriptionTestForException() {
		when(subscriptionRepo.save(Mockito.any(Subscription.class))).thenThrow(SubscriptionNotCreatedException.class);
		Subscription subscription = new Subscription(3, "1 Month Subscription", Date.valueOf(LocalDate.now()), true, new Customer(1));
		assertThrows(SubscriptionNotCreatedException.class, ()->subscriptionService.createSubscription(subscription));
		verify(subscriptionRepo,times(1)).save(subscription);
	}
	
	@Test
	public void getAllSubscriptionsTest() {
		List<Subscription> subscriptions = new ArrayList<>();
		subscriptions.add(new Subscription(1, "1 Month Subscription", Date.valueOf(LocalDate.now()), true, new Customer(1)));
		subscriptions.add(new Subscription(2, "2 Month Subscription", Date.valueOf(LocalDate.now()), true, new Customer(2)));
		when(subscriptionRepo.findAll()).thenReturn(subscriptions);
		assertEquals(subscriptions, subscriptionService.getAllSubscriptions());
		verify(subscriptionRepo,times(1)).findAll();
	}
	
	@Test
	public void getAllSubscriptionsTestForException() {
		when(subscriptionRepo.findAll()).thenThrow(SubscriptionException.class);
		assertThrows(SubscriptionException.class, ()->subscriptionService.getAllSubscriptions());
		verify(subscriptionRepo,times(1)).findAll();
	}
	
	@Test
	public void viewActiveSubscriptionsTest(){
		List<Subscription> subscriptions = new ArrayList<>();
		subscriptions.add(new Subscription(1, "1 Month Subscription", Date.valueOf(LocalDate.now()), true, new Customer(1)));
		subscriptions.add(new Subscription(2, "2 Month Subscription", Date.valueOf(LocalDate.now()), true, new Customer(1)));
		when(subscriptionRepo.viewActiveSubscriptions(1)).thenReturn(subscriptions);
		assertEquals(subscriptions, subscriptionService.viewActiveSubscriptions(1));
		verify(subscriptionRepo,times(1)).viewActiveSubscriptions(1);
	}
	
	@Test
	public void viewActiveSubscriptionsTestForException(){
		when(subscriptionRepo.viewActiveSubscriptions(1)).thenThrow(SubscriptionException.class);
		assertThrows(SubscriptionException.class, ()->subscriptionService.viewActiveSubscriptions(1));
		verify(subscriptionRepo,times(1)).viewActiveSubscriptions(1);
	}
	
	@Test
	public void batchInactiveSubscriptionTest() {
		List<Subscription> subscriptions = new ArrayList<>();
		subscriptions.add(new Subscription(1, "1 Month Subscription", Date.valueOf(LocalDate.now()), true, new Customer(1)));
		subscriptions.add(new Subscription(2, "2 Month Subscription", Date.valueOf(LocalDate.now()), true, new Customer(1)));
		when(subscriptionJdbcRepo.batchInactiveSubscription(subscriptions)).thenReturn("2 Subscriptions inactivated");
		assertEquals("2 Subscriptions inactivated", subscriptionService.batchInactiveSubscription(subscriptions));
		verify(subscriptionJdbcRepo,times(1)).batchInactiveSubscription(subscriptions);
	}
	
	@Test
	public void batchInactiveSubscriptionTestForException() {
		List<Subscription> subscriptions = new ArrayList<>();
		subscriptions.add(new Subscription(1, "1 Month Subscription", Date.valueOf(LocalDate.now()), true, new Customer(1)));
		subscriptions.add(new Subscription(2, "2 Month Subscription", Date.valueOf(LocalDate.now()), true, new Customer(1)));
		when(subscriptionJdbcRepo.batchInactiveSubscription(subscriptions)).thenThrow(SubscriptionException.class);
		assertThrows(SubscriptionException.class, ()->subscriptionService.batchInactiveSubscription(subscriptions));
		verify(subscriptionJdbcRepo,times(1)).batchInactiveSubscription(subscriptions);
	}

}
