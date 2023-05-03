package ru.job4j.job4j_order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_order.model.OrderStatus;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final KafkaTemplate<String, OrderStatus> kafkaTemplate;

    public void sendOrderStatus(OrderStatus orderStatus) {
        kafkaTemplate.send("preorder", orderStatus);
    }
}
