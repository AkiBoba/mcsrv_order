package ru.job4j.job4j_order.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.job4j_order.model.DTO.RequestDishDTO;
import ru.job4j.job4j_order.model.Dish;
import ru.job4j.job4j_order.model.Order;

import java.util.List;

@Service
public class DishService {

    @Value("${dish.api-url}")
    private String url;

    private final RestTemplate client;
    private final KafkaTemplate<String, RequestDishDTO> kafkaTemplate;

    public DishService(RestTemplate client, KafkaTemplate<String, RequestDishDTO> kafkaTemplate) {
        this.client = client;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendListOfIdDishes(RequestDishDTO requestDishDTO) {
        kafkaTemplate.send("sendListOfIdDishes", requestDishDTO);

    }

    public Dish findById(Integer id) {
        String uri = String.format("%s%s/%d", url, "order", id);
        return client.getForEntity(uri, Dish.class).getBody();
    }

    public Void insertInDishOrder(Integer orderId, Integer dishId) {
        String uri = String.format("%s%s/%d/%d", url, "order", orderId, dishId);
        return client.postForObject(uri, Order.class, void.class);
    }

    public List getAllDishes() {
        return client.getForEntity(url, List.class).getBody();
    }
}
