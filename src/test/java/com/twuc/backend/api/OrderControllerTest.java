package com.twuc.backend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.backend.dto.OrderDto;
import com.twuc.backend.dto.ProductDto;
import com.twuc.backend.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderRepository orderRepository;

    private String jsonString;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        orderRepository.deleteAll();
        ProductDto productDto = ProductDto.builder()
                .name("芬达").id(null).price(3).unit("听").imageUrl("https://s.yam.com/9kDww").build();
        OrderDto orderDto = OrderDto.builder().id(null).productId(productDto.getId()).count(1).build();
        orderRepository.save(orderDto);
        ObjectMapper objectMapper = new ObjectMapper();
        jsonString = objectMapper.writeValueAsString(orderDto);
    }

    @Test
    void should_add_new_order() throws Exception {
        orderRepository.deleteAll();
        mockMvc.perform(post("/order").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void should_get_all_orders() throws Exception {
        mockMvc.perform(get("/orders").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(status().isOk());
    }
}
