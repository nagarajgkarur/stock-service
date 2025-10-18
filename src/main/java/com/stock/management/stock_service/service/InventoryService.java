package com.stock.management.stock_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.management.stock_service.domain.Inventory;
import com.stock.management.stock_service.dto.InventoryPayload;
import com.stock.management.stock_service.dto.InventoryResponse;
import com.stock.management.stock_service.dto.InventoryResponseEntity;
import com.stock.management.stock_service.repository.InventoryRepository;
import com.stock.management.stock_service.utility.InventoryUtility;

@Service
public class InventoryService {
	
	@Autowired
	InventoryUtility inventoryUtility;
	
	@Autowired
	InventoryRepository inventoryRepository;

	public InventoryResponseEntity getallInventory() {
		List<Inventory> inventories = inventoryRepository.findAll();
		List<InventoryResponse> inventoryResponses =  inventoryUtility.getInventoryResponses(inventories);
		InventoryResponseEntity inventoryResponseEntity = new InventoryResponseEntity();
		inventoryResponseEntity.setInventoryResponses(inventoryResponses);
		return inventoryResponseEntity;
	}

	public InventoryResponseEntity getInventoryById(Long id) {
		Inventory inventory = inventoryRepository.findById(id).orElseThrow();
		List<Inventory> inventories = new ArrayList<Inventory>();
		inventories.add(inventory);
		List<InventoryResponse> inventoryResponses =  inventoryUtility.getInventoryResponses(inventories);
		InventoryResponseEntity inventoryResponseEntity = new InventoryResponseEntity();
		inventoryResponseEntity.setInventoryResponses(inventoryResponses);
		return inventoryResponseEntity;
	}

	public InventoryResponseEntity createInventory(InventoryPayload inventoryPayload) {
		Inventory inventory = inventoryUtility.getInventory(inventoryPayload);
		inventory = inventoryRepository.save(inventory);
		List<Inventory> inventories = new ArrayList<Inventory>();
		inventories.add(inventory);
		List<InventoryResponse> inventoryResponses =  inventoryUtility.getInventoryResponses(inventories);
		InventoryResponseEntity inventoryResponseEntity = new InventoryResponseEntity();
		inventoryResponseEntity.setInventoryResponses(inventoryResponses);
		return inventoryResponseEntity;
	}

	public InventoryResponseEntity updateInventory(InventoryPayload inventoryPayload) {
		Inventory inventory = inventoryUtility.getInventory(inventoryPayload);
		inventory = inventoryRepository.save(inventory);
		List<Inventory> inventories = new ArrayList<Inventory>();
		inventories.add(inventory);
		List<InventoryResponse> inventoryResponses =  inventoryUtility.getInventoryResponses(inventories);
		InventoryResponseEntity inventoryResponseEntity = new InventoryResponseEntity();
		inventoryResponseEntity.setInventoryResponses(inventoryResponses);
		return inventoryResponseEntity;
	}

	public InventoryResponseEntity searchInventory(String partId) {
		Inventory inventory =  inventoryRepository.findByPartId(partId);
		List<Inventory> inventories = new ArrayList<Inventory>();
		inventories.add(inventory);
		List<InventoryResponse> inventoryResponse = inventoryUtility.getInventoryResponses(inventories);
		InventoryResponseEntity inventoryResponseEntity = new InventoryResponseEntity();
		inventoryResponseEntity.setInventoryResponses(inventoryResponse);
		return inventoryResponseEntity;
	}

	public InventoryResponseEntity updateInventoryStock(int inTakeCount, double price, String partId) {
		Inventory  inventory = inventoryRepository.findByPartId(partId);
		Integer availableCount = inventory.getAvailableCount();
		availableCount = availableCount+inTakeCount;
		inventory.setPrice(price);
		inventory.setAvailableCount(availableCount);
		inventory = inventoryRepository.save(inventory);
		List<Inventory> inventories = new ArrayList<Inventory>();
		inventories.add(inventory);
		List<InventoryResponse> inventoryResponse = inventoryUtility.getInventoryResponses(inventories);
		InventoryResponseEntity inventoryResponseEntity = new InventoryResponseEntity();
		inventoryResponseEntity.setInventoryResponses(inventoryResponse);
		return inventoryResponseEntity;
	}

}
