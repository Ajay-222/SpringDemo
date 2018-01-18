package com.yadav.microservice.limitsservice.bean;

import org.springframework.beans.factory.annotation.Value;

public class LimitConfiguration {

	private int min;
	private int max;
	protected LimitConfiguration(){}
	
	public LimitConfiguration(int min, int max) {
		super();
		this.min = min;
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
}
