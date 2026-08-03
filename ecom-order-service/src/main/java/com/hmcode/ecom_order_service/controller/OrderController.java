package com.hmcode.ecom_order_service.controller;

import com.hmcode.ecom_order_service.services.OrderService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    private OrderService orderService;

    //constructor injection
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/rest/template/{productName}")
    public String placeOrderWithRestTemplate(@PathVariable String productName){
        return orderService.placeOrder(productName);
    }

    @PostMapping("/rest/client/{productName}")
    public  String placeOrderWithRestClient(@PathVariable String productName){
        return orderService.placeOrderWithRestClient(productName);
    }

    @PostMapping("/feign/client/{productName}")
    public String placeOrderWithFeignClient(@PathVariable String productName){
        return orderService.placecOrderWithFeignClient(productName);
    }
}
