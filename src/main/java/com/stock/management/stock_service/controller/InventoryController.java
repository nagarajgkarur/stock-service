package com.stock.management.stock_service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stock.management.stock_service.dto.InventoryPayload;
import com.stock.management.stock_service.dto.InventoryResponseEntity;
import com.stock.management.stock_service.service.InventoryService;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

	@Autowired
	InventoryService inventoryService;
	
	@GetMapping("")
	public ResponseEntity<InventoryResponseEntity> getAllInventory(){
		InventoryResponseEntity inventoryResponseEntity = inventoryService.getallInventory();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(inventoryResponseEntity);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<InventoryResponseEntity> getInventoryById(@PathVariable Long id){
		InventoryResponseEntity inventoryResponseEntity = inventoryService.getInventoryById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(inventoryResponseEntity);
	}
	
	@GetMapping("/search")
	public ResponseEntity<InventoryResponseEntity> searchInventory(@RequestParam(required = false) String partId){
		InventoryResponseEntity  inventoryResponseEntity = inventoryService.searchInventory(partId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(inventoryResponseEntity);
	}
	
	@PostMapping("")
	public ResponseEntity<InventoryResponseEntity> createInventory(@RequestBody InventoryPayload inventoryPayload){
		InventoryResponseEntity inventoryResponseEntity = inventoryService.createInventory(inventoryPayload);
		return ResponseEntity.status(HttpStatus.CREATED).body(inventoryResponseEntity);
	}
	
	@PostMapping("/create")
	public InventoryResponseEntity updateInventory(@RequestBody InventoryPayload inventoryPayload){
		InventoryResponseEntity inventoryResponseEntity = inventoryService.updateInventory(inventoryPayload);
		return inventoryResponseEntity;
	}
	
	@PutMapping("")
	public ResponseEntity<InventoryResponseEntity> updateInventoryStock(@RequestParam(required = true) int inTakeCount, 
			@RequestParam(required = true) double price,
			@RequestParam(required = true) String partId,
			@RequestParam(required = true) String action){
		InventoryResponseEntity inventoryResponseEntity =  inventoryService.updateInventoryStock(inTakeCount,price,partId,action);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(inventoryResponseEntity);
	}
}
