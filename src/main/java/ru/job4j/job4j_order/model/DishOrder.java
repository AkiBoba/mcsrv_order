package ru.job4j.job4j_order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishOrder {
    private Long orderId;
    private List<Integer> dishesId;
}
