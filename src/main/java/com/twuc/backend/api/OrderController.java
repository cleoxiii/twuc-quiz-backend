package com.twuc.backend.api;

import com.twuc.backend.dto.OrderDto;
import com.twuc.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @PostMapping("/order")
    public ResponseEntity<Object> addOrder(@RequestBody OrderDto orderDto) {
        orderRepository.save(orderDto);
        return ResponseEntity.created(URI.create("/order/" + orderDto.getId())).build();
    }
}
