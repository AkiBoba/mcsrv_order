package ru.job4j.job4j_order.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.job4j_order.model.Dish;

@Service
public class DishService {

    @Value("${dish.api-url}")
    private String url;

    private final RestTemplate client;

    public DishService(RestTemplate client) {
        this.client = client;
    }

    public Dish findById(String id) {
        String uri = String.format("%s%s", url, id);
        return client.getForEntity(uri, Dish.class).getBody();
    }
}
