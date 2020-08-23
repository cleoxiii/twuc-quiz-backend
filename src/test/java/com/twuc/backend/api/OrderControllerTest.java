package com.twuc.backend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.backend.dto.OrderDto;
import com.twuc.backend.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void should_add_new_order() throws Exception {
        ProductDto productDto = ProductDto.builder()
                .name("芬达").id(null).price(3).unit("听").imageUrl("https://s.yam.com/9kDww").build();
        OrderDto orderDto = OrderDto.builder().id(null).productId(productDto.getId()).count(1).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(orderDto);

        mockMvc.perform(post("/order").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
