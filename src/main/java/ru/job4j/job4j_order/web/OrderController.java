package ru.job4j.job4j_order.web;

import org.springframework.web.bind.annotation.*;
import ru.job4j.job4j_order.model.Dish;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.model.OrderDTO;
import ru.job4j.job4j_order.service.DishService;
import ru.job4j.job4j_order.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;
    private final DishService dishService;

    public OrderController(OrderService service, DishService dishService) {
        this.service = service;
        this.dishService = dishService;
    }

    @GetMapping("/")
    public List<Order> findAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public OrderDTO findById(@PathVariable int id) {
        return new OrderDTO(service.getById(id), dishService.findById("1"));
    }

    @PostMapping("/add")
    public void create(@RequestBody Order order) {
        order.setStatus(order.getStatus());
        service.create(order);
    }

    @PostMapping("/delete")
    public void deleteDish(@RequestBody Order order) {
        service.delete(order);
    }
}
