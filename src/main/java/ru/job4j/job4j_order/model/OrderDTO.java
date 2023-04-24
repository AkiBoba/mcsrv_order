package ru.job4j.job4j_order.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDTO {

    private Order order;
    private Dish dish;
}
