package ru.job4j.job4j_order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.job4j_order.model.DishOrder;

import java.util.List;

@Slf4j
@Service
public class DishService {

    @Value("${dish.api-url}")
    private String url;

    private final RestTemplate client;
    private final KafkaTemplate<String, DishOrder> kafkaTemplate;
    private final DishesFeignClients feignClients;

    public DishService(RestTemplate client, KafkaTemplate<String, DishOrder> kafkaTemplate, DishesFeignClients feignClients) {
        this.client = client;
        this.kafkaTemplate = kafkaTemplate;
        this.feignClients = feignClients;
    }

    public void sendDishOrder(DishOrder dishOrder) {
        log.info("");
        kafkaTemplate.send("dish_order", dishOrder);

    }

    public List getAllDishes() {
//        return client.getForEntity(url, List.class).getBody();
        return feignClients.getAll();
    }
}
