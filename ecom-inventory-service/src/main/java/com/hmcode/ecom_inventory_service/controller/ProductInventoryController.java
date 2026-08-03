package com.hmcode.ecom_inventory_service.controller;

import com.hmcode.ecom_inventory_service.entity.Inventory;
import com.hmcode.ecom_inventory_service.services.ProductInventoryService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/inventory")
public class ProductInventoryController {

    public ProductInventoryService productInventoryService;

    public ProductInventoryController(ProductInventoryService productInventoryService) {
        this.productInventoryService = productInventoryService;
    }

    @GetMapping("/{productName}")
    public Inventory checkProductInventory(@PathVariable String productName) throws InterruptedException {
        System.out.println("Checking inventory for product Name : " + productName);
        //Thread.sleep(10000);
        return productInventoryService.checkProductInventory(productName);
    }

    @PostMapping
    public String addProductToInventory(@RequestBody Inventory inventory){
        System.out.println("Adding product to inventory . "+ inventory);
        return productInventoryService.addProduct(inventory);
    }

    @PutMapping("/{productName}")
    public String updateProductInInventory(@RequestBody Inventory inventory, @PathVariable String productName){
        System.out.println("Updateing product in inventory : " + inventory + "for product name " + productName);
        return productInventoryService.updateProduct(inventory, productName);
    }

    @DeleteMapping("/{productName}")
    public String deleteProductfromInventory(@PathVariable String productName){
        System.out.println("Delete product from inventory : " + productName);
        return productInventoryService.deleteProduct(productName);
    }

    @PostMapping("/addAll")
    public String AllProductsToInventory(@RequestBody List<Inventory> inventoryList){
        if(!CollectionUtils.isEmpty(inventoryList)){
            inventoryList.stream().forEach(inventory -> productInventoryService.addProduct(inventory));
            return "Product Added";
        }
        return "List is empty";
    }

    @GetMapping("/getall")
    public List<Inventory> getAllProducts(){
        return productInventoryService.getAllProduct();
    }
}
