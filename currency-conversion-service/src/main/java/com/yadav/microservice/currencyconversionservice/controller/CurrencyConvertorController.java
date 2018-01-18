package com.yadav.microservice.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.yadav.microservice.currencyconversionservice.bean.CurrencyConversionBean;
import com.yadav.microservice.currencyconversionservice.client.CurrencyExchangeProxy;


@RestController
public class CurrencyConvertorController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurreny(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) 
	{
		
		Map<String,String> uriVariables = new HashMap<String,String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversionBean.class, 
				uriVariables );
		CurrencyConversionBean body = responseEntity.getBody();
		return new CurrencyConversionBean(body.getId(),from,to,body.getConversionMultiple(),
				quantity,
				quantity.multiply(body.getConversionMultiple()),body.getPort());
	}
	

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	
	public CurrencyConversionBean convertCurrenyFeign(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) 
	{
		CurrencyConversionBean response = proxy.retrieveExchange(from, to);
		logger.info("{}",response);
		
		return new CurrencyConversionBean(response.getId(),from,to,response.getConversionMultiple(),
				quantity,
				quantity.multiply(response.getConversionMultiple()),response.getPort());
	}

}
