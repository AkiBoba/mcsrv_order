package ru.job4j.job4j_order.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.job4j_order.model.Dish;
import ru.job4j.job4j_order.model.Order;

@Service
public class DishService {

    @Value("${dish.api-url}")
    private String url;

    private final RestTemplate client;

    public DishService(RestTemplate client) {
        this.client = client;
    }

    public Dish findById(Integer id) {
        String uri = String.format("%s%d", url, id);
        return client.getForEntity(uri, Dish.class).getBody();
    }

    public Void insertInDishOrder(Integer orderId, Integer dishId) {
        String uri = String.format("%s%d/%d", url, orderId, dishId);
        return client.postForObject(uri, Order.class, void.class);
    }
}
