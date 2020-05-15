package com.cts.crm.config;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class TransactionUtil {
	
	@Autowired
	DataServiceConfig properties;
	
	public String setTransactionId(HttpHeaders headers){
		String transactionId=headers.get("Transaction-Id").get(0);
		MDC.clear();
		MDC.put("transactionId",transactionId);
		MDC.put("service", properties.getServiceName());
		return transactionId;
	}
}
