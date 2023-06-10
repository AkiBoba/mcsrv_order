package ru.job4j.job4j_order.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.job4j_order.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
