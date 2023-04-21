package ru.job4j.job4j_order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "status")
public class Status {
    @Id
    private Integer id;
    private String name;

    public Status(Integer id) {
        this.id = id;
    }

    public Status() {

    }
}
