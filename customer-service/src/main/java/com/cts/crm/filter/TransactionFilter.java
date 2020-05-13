package com.cts.crm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TransactionFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String transType = req.getHeader("Transaction-Type");
		long timestamp = System.nanoTime();
		String transId = transType + "_" + timestamp;
		MDC.clear();
		MDC.put("transId", transId);
		chain.doFilter(request, response);
		log.info("Starting a Transaction for Request -> {}",req.getRequestURI());
		
	}

}
