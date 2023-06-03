package ru.job4j.job4j_order.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import ru.job4j.job4j_order.model.*;
import ru.job4j.job4j_order.model.DTO.OrderDTO;
import ru.job4j.job4j_order.model.DTO.RequestOrderDTO;
import ru.job4j.job4j_order.service.DishService;
import ru.job4j.job4j_order.service.KitchenService;
import ru.job4j.job4j_order.service.NotificationService;
import ru.job4j.job4j_order.service.OrderService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KitchenService kitchenService;
    private final NotificationService notificationService;
    private final OrderService service;
    private final DishService dishService;

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
        notificationService.sendOrderStatus(new OrderStatus(order.getId(), order.getStatus().getId()));
        kitchenService.sendOrder(dto);
    }

    @PostMapping("/delete")
    public void deleteDish(@RequestBody Order order) {
        service.delete(order);
    }

    @PostMapping("/setstatus/{orderId}")
    public void setStatus(@PathVariable Integer orderId, @RequestBody Status status) {
        Order order = service.getById(orderId);
        order.setStatus(status);
        service.updateOrder(order);
        notificationService.sendOrderStatus(new OrderStatus(order.getId(), order.getStatus().getId()));
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveOrder(@RequestBody List<Integer> dishes) {
        log.info(dishes.toString());
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
