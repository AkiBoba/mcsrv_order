package ru.job4j.job4j_order.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.model.DTO.OrderDTO;
import ru.job4j.job4j_order.model.DTO.RequestOrderDTO;
import ru.job4j.job4j_order.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository repository;

    @Transactional
    public RequestOrderDTO create(OrderDTO order) {
        var savedOrder = repository.save(order.getOrder());
        return new RequestOrderDTO(savedOrder.getId(), order.getDish().getId());
    }

    @Transactional
    public void updateOrder(Order order) {
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
