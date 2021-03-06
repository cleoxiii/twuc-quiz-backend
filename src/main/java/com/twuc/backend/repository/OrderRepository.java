package com.twuc.backend.repository;

import com.twuc.backend.dto.OrderDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<OrderDto, Integer> {
    @Override
    List<OrderDto> findAll();

    Optional<OrderDto> findByProductId(Integer id);
}
