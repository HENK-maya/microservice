package com.hmcode.ecom_order_service.clients.config;

import com.hmcode.ecom_order_service.bean.Inventory;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import tools.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class CustomeInventoryEncoder implements Encoder {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void encode(Object object, Type type, RequestTemplate requestTemplate) throws EncodeException {
        try{
            if(object instanceof Inventory inventory){
                Map<String, Object> data = new HashMap<>();
                data.put("id", inventory.getId());
                data.put("productName", inventory.getProductName());
                data.put("quantity", inventory.getQuantity());
                String json = objectMapper.writeValueAsString(data);
                requestTemplate.body(json);
                requestTemplate.header("Content-Type", "application/json");
            }
        }catch (Exception e){
            throw new RuntimeException("Encoding Failed .");
        }
    }
}
