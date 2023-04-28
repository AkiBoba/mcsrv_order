package ru.job4j.job4j_order.model;

import lombok.Data;

@Data
public class RequestOrderDTO {
    Integer id;
    Integer dishId;

    public RequestOrderDTO(Integer id, Integer dishId) {
        this.id = id;
        this.dishId = dishId;
    }
}
