package ru.job4j.job4j_order.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
