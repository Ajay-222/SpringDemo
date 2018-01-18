package com.yadav.microservice.currencyexchangeservice.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yadav.microservice.currencyexchangeservice.bean.ExchangeValue;

public interface ExchangeRepo extends JpaRepository<ExchangeValue, Long> {

	ExchangeValue findByFromAndTo(String from,String to);
	
}
