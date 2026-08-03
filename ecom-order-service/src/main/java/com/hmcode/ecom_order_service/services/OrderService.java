package com.hmcode.ecom_order_service.services;

import com.hmcode.ecom_order_service.bean.Inventory;
import com.hmcode.ecom_order_service.clients.InventoryClient;
import com.hmcode.ecom_order_service.config.RestTemplateConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;

@Service
public class OrderService {

    private RestTemplate restTemplate;
    private RestClient restClient;
    private InventoryClient inventoryClient;

    public OrderService(RestTemplate restTemplate, RestClient restClient, InventoryClient inventoryClient) {
        this.restTemplate = restTemplate;
        this.restClient = restClient;
        this.inventoryClient = inventoryClient;
    }

    public String placeOrder(String productName){
        //todo need to connect to inventory to get the product details.
        String response = restTemplate.getForObject(
                "http://localhost:8081/v1/inventory/" + productName,
                String.class
        );
        return response.equalsIgnoreCase("IN STOCK") ? "Order placed successfully for product : " + productName : "Product Out of stuck !";
    }

    public String placeOrderWithRestClient(String productName) {
        String response1 = restClient.get()
                .uri("http://localhost:8081/v1/inventory/{productName}", productName)
                .retrieve()
                .body(String.class);
        ResponseEntity<Inventory> response = restClient.get()
                .uri("http://localhost:8081/v1/inventory/{productName}", productName)
                .retrieve()
                .toEntity(Inventory.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        //update the quantity by minus 1
        updateProductToInventory(response.getBody());
        return response.getBody() != null && response.getBody().getQuantity() >0 ?
        "Order Places successfully using RestClient for product : " + productName : "Out Of Stuck !";
    }

    private void updateProductToInventory(Inventory inventory) {
        inventory.setQuantity(inventory.getQuantity()-1);
        restClient.put()
                .uri("http://localhost:8081/v1/inventory/{productName}", inventory.getProductName())
                .body(inventory)
                .retrieve()
                .toBodilessEntity();
    }

    public String placecOrderWithFeignClient(String productName) {
        Inventory inventory = inventoryClient.getProductFromInventoryByProductName(productName);
        int quantity = inventory.getQuantity();
        //update the quantity by minus 1
        updateProductToInventoryUsingFeignClient(inventory);
        return inventory != null && quantity >0 ?
                "Order Places successfully using FeignClient for product : " + productName : "Out Of Stuck !";
    }

    private void updateProductToInventoryUsingFeignClient(Inventory inventory) {
        inventory.setQuantity(inventory.getQuantity()-1);
        inventoryClient.updateProductCountInInventory(inventory, inventory.getProductName());
    }
}
