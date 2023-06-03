package ru.job4j.job4j_order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_order.model.DTO.RequestOrderDTO;

@Service
@RequiredArgsConstructor
public class KitchenService {
    private final KafkaTemplate<String, RequestOrderDTO> kafkaTemplate;

    public void sendOrder(RequestOrderDTO requestOrderDTO) {
        kafkaTemplate.send("cooked_order", requestOrderDTO);
    }
}
