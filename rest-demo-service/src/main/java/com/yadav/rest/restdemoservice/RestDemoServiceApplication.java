package com.yadav.rest.restdemoservice;


import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class RestDemoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestDemoServiceApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localResolver()
	{
		SessionLocaleResolver localResolver = new SessionLocaleResolver();
		localResolver.setDefaultLocale(Locale.US);
		return localResolver;	
	}
	
	@Bean
	public MessageSource bundleMessageSource()
	{
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setFallbackToSystemLocale(false);
		return messageSource;
	}
}
