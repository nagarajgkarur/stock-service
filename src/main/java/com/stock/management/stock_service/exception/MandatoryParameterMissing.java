package com.stock.management.stock_service.exception;

import java.util.Arrays;
import java.util.List;

public class MandatoryParameterMissing extends RuntimeException{
	
	private String message;
	private List<String> data;
	
	public MandatoryParameterMissing(String message,String data) {
		this.message=message;
		this.data= Arrays.asList(data.split(","));
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	
	

}
