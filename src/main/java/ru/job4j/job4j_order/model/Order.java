package ru.job4j.job4j_order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("orders")
public class Order {
    @Id
    private Long id;

    @Column("customer")
    private Long customer;

    @Column("status")
    private Integer status;

    public Order(Integer status) {
        this.status = status;
    }
}
