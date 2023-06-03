package ru.job4j.job4j_order.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.job4j.job4j_order.model.Dish;
import ru.job4j.job4j_order.model.Order;

@Data
@AllArgsConstructor
public class OrderDTO {

    private Order order;
    private Dish dish;
}
