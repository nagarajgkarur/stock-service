package com.stock.management.stock_service.exception;

import java.util.List;

public class ErrorResponseModel {
	
	private int code;
	private String message;
	private List<String> data;
	
	public ErrorResponseModel(String message,List<String> data) {
		this.message=message;
		this.data=data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
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
