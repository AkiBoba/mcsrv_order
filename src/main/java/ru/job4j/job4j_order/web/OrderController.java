package ru.job4j.job4j_order.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import ru.job4j.job4j_order.model.*;
import ru.job4j.job4j_order.repository.OrderRepository;
import ru.job4j.job4j_order.service.DishService;
import ru.job4j.job4j_order.service.KitchenService;
import ru.job4j.job4j_order.service.NotificationService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KitchenService kitchenService;
    private final NotificationService notificationService;
    private final OrderRepository repository;
    private final DishService dishService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody List<Integer> dishes) {
        Order order = repository.save(new Order(1));
        dishService.sendDishOrder(new DishOrder(order.getId(), dishes));
        return new ResponseEntity<>("Number of order" + order.getId(), HttpStatus.OK);
    }
}
