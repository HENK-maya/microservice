package com.hmcode.ecom_order_service.clients;

import com.hmcode.ecom_order_service.bean.Inventory;
import com.hmcode.ecom_order_service.clients.config.InventoryFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ecom-inventory-service",
        configuration = InventoryFeignClientConfig.class)
public interface InventoryClient {

    @GetMapping("/v1/inventory/{productName}")
    Inventory getProductFromInventoryByProductName(@PathVariable String productName);

    @PutMapping("/v1/inventory/{productName}")
    String updateProductCountInInventory(@RequestBody Inventory inventory, @PathVariable String productName);
}
