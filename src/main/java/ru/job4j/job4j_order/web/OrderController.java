package ru.job4j.job4j_order.web;

import org.springframework.web.bind.annotation.*;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Order> findAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping("/add")
    public void create(@RequestBody Order order) {
        order.setStatus(order.getStatus());
        service.create(order);
    }

    @PostMapping("/delete")
    public void deleteDish(@RequestBody Order order)
    {
        service.delete(order);
    }
}
