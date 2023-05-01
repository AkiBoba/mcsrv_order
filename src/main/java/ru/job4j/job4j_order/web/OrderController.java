package ru.job4j.job4j_order.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import ru.job4j.job4j_order.model.*;
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
        Order order = service.getById(id);
        return new OrderDTO(order, dishService.findById(order.getId()));
    }

    @PostMapping("/add")
    public void create(@RequestBody OrderDTO orderDTO) throws JsonProcessingException {
        Order order = orderDTO.getOrder();
        order.setStatus(new Status(1));
        RequestOrderDTO dto = service.create(orderDTO);
        dishService.insertInDishOrder(order.getId(), orderDTO.getDish().getId());
        String orderStatus = OBJECT_MAPPER.writeValueAsString(new OrderStatus(order.getId(), order.getStatus().getId()));
        kafkaTemplate.send("preorder", orderStatus);
        String requestDTO = OBJECT_MAPPER.writeValueAsString(dto);
        kafkaTemplate.send("cooked_order", requestDTO);

    }

    @PostMapping("/delete")
    public void deleteDish(@RequestBody Order order) {
        service.delete(order);
    }

    @PostMapping("/setstatus/{orderId}")
    public void setStatus(@PathVariable Integer orderId, @RequestBody Status status) throws JsonProcessingException {
        Order order = service.getById(orderId);
        order.setStatus(status);
        service.updateOrder(order);
        String orderStatus = OBJECT_MAPPER.writeValueAsString(new OrderStatus(order.getId(), order.getStatus().getId()));
        kafkaTemplate.send("preorder", orderStatus);
    }
}
