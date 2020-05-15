package com.cts.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cts.crm.config.DataServiceConfig;
import com.cts.crm.model.Subscription;
import com.cts.crm.repo.SubscriptionJdbcRepo;
import com.cts.crm.repo.SubscriptionJpaRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Repository
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	SubscriptionJpaRepo subscriptionJpaRepo;
	
	@Autowired
	SubscriptionJdbcRepo subscriptionJdbcRepo;
	
	@Autowired
	DataServiceConfig properties;
	
	@Override
	public Subscription getSubscriptionById(int id) {
		log.info("Inside Get Subscription By Id [Subscription Service]");
		if(properties.getJpaEnable().equalsIgnoreCase("Y"))
			return subscriptionJpaRepo.findById(id).orElse(null);
		else
			return subscriptionJdbcRepo.findById(id).orElse(null);
	}
	
	@Override
	public Subscription createSubscription(Subscription subscription) {
		log.info("Inside Create Subscription [Subscription Service]");
		if(properties.getJpaEnable().equalsIgnoreCase("Y"))
			return subscriptionJpaRepo.save(subscription);
		else
			return subscriptionJdbcRepo.save(subscription);
	}
	
	@Override
	public List<Subscription> getAllSubscriptions() {
		log.info("Inside Get All Subscriptions [Subscription Service]");
		if(properties.getJpaEnable().equalsIgnoreCase("Y"))
			return subscriptionJpaRepo.findAll();
		else
			return subscriptionJdbcRepo.findAll();
	}
	
	@Override
	public List<Subscription> viewActiveSubscriptions(int customerId) {
		log.info("Inside View Active Subscriptions [Subscription Service]");
		if(properties.getJpaEnable().equalsIgnoreCase("Y"))
			return subscriptionJpaRepo.viewActiveSubscriptions(customerId);
		else
			return subscriptionJdbcRepo.viewActiveSubscriptions(customerId);
	}
	
	@Override
	public void batchInactiveSubscription(List<Subscription> subscriptions) {
		log.info("Inside Batch Inactive Subscriptions [Subscription Service]");
		subscriptionJdbcRepo.batchInactiveSubscription(subscriptions);
	}
	
	//Unused in this Project
	
	@Override
	public void inactiveSubscription(int id) {
		subscriptionJpaRepo.inactiveSubscription(id);
	}
	
	@Override
	public List<Subscription> getAllSubscriptionsOfToday() {
		return subscriptionJpaRepo.findAllSubscriptionsOfToday();
	}

}
