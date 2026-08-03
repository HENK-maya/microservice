package com.hmcode.ecom_inventory_service.repository;


import com.hmcode.ecom_inventory_service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRespository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductName(String productId);
}
