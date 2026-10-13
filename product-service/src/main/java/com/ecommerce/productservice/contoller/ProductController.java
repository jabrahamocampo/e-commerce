package com.ecommerce.productservice.contoller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController 
public class ProductController {

    @GetMapping("/api/products/health")
    public String getMethodName() {
        return new String("Product service is running");
    }
    

}
