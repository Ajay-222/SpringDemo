package com.yadav.microservice.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yadav.microservice.limitsservice.bean.LimitConfiguration;
import com.yadav.microservice.limitsservice.config.Configuration;

@RestController
public class LimitController {
	
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitFromConfiguration()
	{
		return new LimitConfiguration(configuration.getMinimum(),configuration.getMaximum());
	}
	
	@GetMapping("/fault-exam")
	@HystrixCommand(fallbackMethod="fallBackRetriveConfiguration")
	public LimitConfiguration retrieveLimitFromConfigurationTest()
	{
		throw new RuntimeException("Exception");
	}

	public LimitConfiguration fallBackRetriveConfiguration()
	{
		return new LimitConfiguration(99,999);
	}
}
