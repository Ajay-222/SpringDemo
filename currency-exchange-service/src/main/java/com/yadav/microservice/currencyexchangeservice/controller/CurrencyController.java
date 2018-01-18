package com.yadav.microservice.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yadav.microservice.currencyexchangeservice.Repo.ExchangeRepo;
import com.yadav.microservice.currencyexchangeservice.bean.ExchangeValue;

@RestController
public class CurrencyController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment enviroment;
	
	@Autowired
	private ExchangeRepo exchangeRepo;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchange(@PathVariable String from, @PathVariable String to)
	{
		ExchangeValue ext= exchangeRepo.findByFromAndTo(from, to);
	//	 new ExchangeValue(1000L,from,to,BigDecimal.valueOf(65));
		ext.setPort(Integer.valueOf(enviroment.getProperty("local.server.port")));
		logger.info("{}",ext);
		return ext;
		
	}

}
