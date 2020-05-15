package com.cts.crm.config;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionUtil {
	
	@Autowired
	SchedulerServiceConfig properties;
	
	public String generateTransactionId(String transType) {
		long timestamp = System.nanoTime();
		String transId = transType + "_" + timestamp;
		MDC.clear();
		MDC.put("transactionId", transId);
		MDC.put("service", properties.getServiceName());
		return transId;
	}

}
