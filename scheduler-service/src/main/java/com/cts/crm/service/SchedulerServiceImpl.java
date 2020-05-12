package com.cts.crm.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.cts.crm.model.Subscription;
import com.cts.crm.service.rest.DataServiceRestTemplate;

@Service
public class SchedulerServiceImpl implements SchedulerService {
	
	@Autowired
	DataServiceRestTemplate dataServiceRestTemplate;

	@Override
	@Async
	public void inactiveSubscription() {
		List<Subscription> subscriptions=dataServiceRestTemplate.getAllSubscriptions().getBody();
		List<Subscription> todaySubscriptions = subscriptions.parallelStream().filter((sub)->sub.getExpiryDate()
				.toLocalDate().equals(LocalDate.now()))
				.collect(Collectors.toList());
		dataServiceRestTemplate.batchInactiveSubscriptions(todaySubscriptions);
//		todaySubscriptions.forEach((sub)->log.info("Subscription: {} is now inactive", sub.getId()));
	}
	
}
