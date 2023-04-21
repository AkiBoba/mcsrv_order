package ru.job4j.job4j_order.service;

import org.springframework.stereotype.Service;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public void create(Order order) {
        repository.save(order);
    }

    public void delete(Order order) {
        repository.delete(order);
    }

    public List<Order> getAll() {
        var result = new ArrayList<Order>();
        for (var order: repository.findAll()) {
            result.add(order);
        }
        return result;
    }

    public Order getById(int id) {
        return repository.findById(id).orElseGet(null);
    }

}
