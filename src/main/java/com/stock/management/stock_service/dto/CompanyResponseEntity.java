package com.stock.management.stock_service.dto;

import java.util.List;

public class CompanyResponseEntity {
	
	private int offset;
	private int max;
	private int totalRecords;
	private List<CompanyResponse> companyResponses;
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<CompanyResponse> getCompanyResponses() {
		return companyResponses;
	}
	public void setCompanyResponses(List<CompanyResponse> companyResponses) {
		this.companyResponses = companyResponses;
	}
	
	
}
