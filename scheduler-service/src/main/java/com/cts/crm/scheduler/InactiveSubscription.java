package com.cts.crm.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cts.crm.service.SchedulerService;

@Component
public class InactiveSubscription {
	
	@Autowired
	SchedulerService schedulerService;
	
	@Scheduled(cron="${scheduler.inactive.cron}")
//	@Scheduled(fixedRate = 10000)
	public void inactiveSubscription() {
		schedulerService.inactiveSubscription();
	}

}