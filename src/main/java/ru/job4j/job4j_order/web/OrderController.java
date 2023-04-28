package ru.job4j.job4j_order.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.model.OrderDTO;
import ru.job4j.job4j_order.model.RequestOrderDTO;
import ru.job4j.job4j_order.service.DishService;
import ru.job4j.job4j_order.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final OrderService service;
    private final DishService dishService;

    public OrderController(KafkaTemplate<String, String> kafkaTemplate, OrderService service, DishService dishService) {
        this.kafkaTemplate = kafkaTemplate;
        this.service = service;
        this.dishService = dishService;
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @GetMapping("/")
    public List<Order> findAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public OrderDTO findById(@PathVariable int id) {
        return new OrderDTO(service.getById(id), dishService.findById("1"));
    }

    @PostMapping("/add")
    public void create(@RequestBody Order order) throws JsonProcessingException {
        order.setStatus(order.getStatus());
        RequestOrderDTO dto = service.create(order);
        String requestDTO = OBJECT_MAPPER.writeValueAsString(dto);
        kafkaTemplate.send("job4j_orders", requestDTO);

    }

    @PostMapping("/delete")
    public void deleteDish(@RequestBody Order order) {
        service.delete(order);
    }
}
