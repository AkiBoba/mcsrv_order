package ru.job4j.job4j_order.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderDTO {
    Integer id;
    Integer dishId;
}
