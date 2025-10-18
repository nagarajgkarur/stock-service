package com.stock.management.stock_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.management.stock_service.domain.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	Inventory findByPartId(String partId);

}
