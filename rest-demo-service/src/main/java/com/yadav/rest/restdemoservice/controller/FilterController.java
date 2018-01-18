package com.yadav.rest.restdemoservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.yadav.rest.restdemoservice.model.SomeBean;

@RestController
public class FilterController {
	
	@GetMapping("/filter")
	public SomeBean getSomeBean()
	{
		return new SomeBean("value1","value2","value3");
	}

	@GetMapping("/filter-list")
	public List<SomeBean> getSomeBeanList()
	{
		return Arrays.asList(new SomeBean("value1","value2","value3"),
				new SomeBean("value12","value22","value23"));
	}
	
	@GetMapping("/filter-dyn")
	public MappingJacksonValue getSomeBeanListDyn()
	{
		SomeBean someBean = new SomeBean("value1","value2","value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field3","field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		
		
		mapping.setFilters(filters);
				
				
		return mapping;
	}
}
