package com.cts.crm.filter;

import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ZuulLoggingFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
//		log.info("Request -> {} ",context.getRequest().getRequestURI());
		UUID requestId = UUID.randomUUID();
		MDC.clear();
		MDC.put("Req Id", String.valueOf(requestId));
		log.info("Request -> {} ",context.getRequest().getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}