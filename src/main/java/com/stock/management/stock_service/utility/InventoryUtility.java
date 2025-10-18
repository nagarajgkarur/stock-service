package com.stock.management.stock_service.utility;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.stock.management.stock_service.domain.Inventory;
import com.stock.management.stock_service.dto.CompanyResponse;
import com.stock.management.stock_service.dto.CompanyResponseEntity;
import com.stock.management.stock_service.dto.InventoryPayload;
import com.stock.management.stock_service.dto.InventoryResponse;
import com.stock.management.stock_service.dto.ProductResponse;
import com.stock.management.stock_service.dto.ProductResponseEntity;
import com.stock.management.stock_service.exception.MandatoryParameterMissing;
import com.stock.management.stock_service.repository.InventoryRepository;


@Component
public class InventoryUtility {

	@Autowired
	InventoryRepository inventoryRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	public List<InventoryResponse> getInventoryResponses(List<Inventory> inventories) {
		return inventories.stream().map(e->getInventoryResponse(e)).collect(Collectors.toList());
	}
	
	public InventoryResponse getInventoryResponse(Inventory inventory) {
		InventoryResponse inventoryResponse = new InventoryResponse();
		inventoryResponse.setId(inventory.getId());
		inventoryResponse.setPartId(inventory.getPartId());
		inventoryResponse.setPartName(inventory.getPartName());
		inventoryResponse.setPartDescription(inventory.getPartDescription());
		inventoryResponse.setCompanyId(inventory.getCompanyId());
		inventoryResponse.setCompanyName(inventory.getCompanyName());
		inventoryResponse.setPrice(inventory.getPrice());
		inventoryResponse.setAvailableCount(inventory.getAvailableCount());
		return inventoryResponse;
	}

	public Inventory getInventory(InventoryPayload inventoryPayload) {
		String partId = inventoryPayload.getPartId();
		Inventory inventory = inventoryRepository.findByPartId(partId);
		if(inventory == null) {
			inventory = new Inventory();
			if(StringUtils.isNotBlank(inventoryPayload.getPartId())) {
				inventory.setPartId(inventoryPayload.getPartId());
			}
			ProductResponseEntity productResponseEntity = restTemplate.getForObject("http://product-service/api/v1/product/search?partId="+inventoryPayload.getPartId(), ProductResponseEntity.class);
			if(productResponseEntity.getProducts() != null) {
				ProductResponse productResponse = productResponseEntity.getProducts().get(0);
				if(StringUtils.isNotBlank(productResponse.getPartName())) {
					inventory.setPartName(productResponse.getPartName());
				}
				if(StringUtils.isNotBlank(productResponse.getPartDescription())) {
					inventory.setPartDescription(productResponse.getPartDescription());
				}
			}
			CompanyResponseEntity companyResponseEntity = restTemplate.getForObject("http://product-service/api/v1/company/"+inventoryPayload.getCompanyId(), CompanyResponseEntity.class);
			if(companyResponseEntity.getCompanyResponses() != null) {
				CompanyResponse companyResponse = companyResponseEntity.getCompanyResponses().get(0);
				if(StringUtils.isNotBlank(companyResponse.getCompanyName())) {
					inventory.setCompanyName(companyResponse.getCompanyName());
				}
			}
			if(inventoryPayload.getAvailableCount() != null) {
				inventory.setAvailableCount(inventoryPayload.getAvailableCount());
			}
			
			if(inventoryPayload.getCompanyId() !=null) {
				inventory.setCompanyId(inventoryPayload.getCompanyId());
			}
			if(inventoryPayload.getPrice() != null) {
				inventory.setPrice(inventoryPayload.getPrice());
			}
		}else {
			int currentCount = inventory.getAvailableCount();
			currentCount = currentCount+inventoryPayload.getAvailableCount();
			inventory.setAvailableCount(currentCount);
			inventory.setPrice(inventoryPayload.getPrice());
		}
			
		
		return inventory;
	}

	private void validateInventory(InventoryPayload inventoryPayload) {
		
		if(StringUtils.isBlank(inventoryPayload.getPartId())) {
			throw new MandatoryParameterMissing("Mandatory parameter missing", "partId");
		}
		if(StringUtils.isBlank(inventoryPayload.getPartName())) {
			throw new MandatoryParameterMissing("Mandatory parameter missing", "partName");
		}
		if(StringUtils.isBlank(inventoryPayload.getPartDescription())) {
			throw new MandatoryParameterMissing("Mandatory parameter missing", "description");
		}
		if(inventoryPayload.getCompanyId() == null) {
			throw new MandatoryParameterMissing("Mandatory parameter missing", "companyId");
		}
		if(StringUtils.isBlank(inventoryPayload.getCompanyName())) {
			throw new MandatoryParameterMissing("Mandatory parameter missing", "companyName");
		}
		if(inventoryPayload.getAvailableCount() != null) {
			throw new MandatoryParameterMissing("Mandatory parameter missing", "availableCount");
		}
		if(inventoryPayload.getPrice() != null) {
			throw new MandatoryParameterMissing("Mandatory parameter missing", "price");
		}
	}

}
