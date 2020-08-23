package com.twuc.backend.api;

import com.twuc.backend.dto.ProductDto;
import com.twuc.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }
}
