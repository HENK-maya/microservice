package com.hmcode.ecom_inventory_service.services;

import com.hmcode.ecom_inventory_service.entity.Inventory;
import com.hmcode.ecom_inventory_service.repository.InventoryRespository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInventoryService {

    @Autowired
    InventoryRespository inventoryRespository;

    public Inventory checkProductInventory(String productName){
        if (StringUtils.isNotBlank(productName)) {
            Optional<Inventory> inventory = inventoryRespository.findByProductName(productName);
            if(inventory.isPresent()){
                return inventory.get();
            }
        }
        return null;
    }

    public String addProduct(Inventory inventory){
        inventoryRespository.save(inventory);
        return "Product Added";
    }

    public String updateProduct(Inventory inventory, String productName){
        if (StringUtils.isNotBlank(productName)) {
            Optional<Inventory> inventoryRes = inventoryRespository.findByProductName(productName);
            if(inventoryRes.isPresent()){
                Inventory entity = mapBeanToEntity(inventoryRes.get(), inventory);
                inventoryRespository.save(entity);
            }
        }
        return "Product Updated";
    }

    private Inventory mapBeanToEntity(Inventory entity, Inventory bean) {
        entity.setProductName(bean.getProductName());
        entity.setQuantity(bean.getQuantity());
        return entity;
    }

    public String deleteProduct(String productName){
        if (StringUtils.isNotBlank(productName)) {
            Optional<Inventory> inventoryRes = inventoryRespository.findByProductName(productName);
            if(inventoryRes.isPresent()){
                inventoryRespository.deleteById(inventoryRes.get().getId());
            }
        }
        return "Product Deleted";
    }

    public List<Inventory> getAllProduct() {
        return inventoryRespository.findAll();
    }
}
