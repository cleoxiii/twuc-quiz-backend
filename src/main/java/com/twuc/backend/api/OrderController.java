package com.twuc.backend.api;

import com.twuc.backend.dto.OrderDto;
import com.twuc.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @PostMapping("/order")
    public ResponseEntity<Object> addOrder(@RequestBody OrderDto orderDto) {
        Optional<OrderDto> dtoOptional = orderRepository.findByProductId(orderDto.getProductId());
        OrderDto currentOrder;
        if(dtoOptional.isPresent()) {
            currentOrder = dtoOptional.get();
            currentOrder.setCount(currentOrder.getCount() + 1);
        } else {
            currentOrder = orderDto;
        }
        currentOrder = orderRepository.save(currentOrder);
        return ResponseEntity.created(URI.create("/order/" + currentOrder.getId())).build();
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @DeleteMapping("/order{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id) {
        orderRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
