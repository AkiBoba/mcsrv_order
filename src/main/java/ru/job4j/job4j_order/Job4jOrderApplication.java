package ru.job4j.job4j_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.out;

@SpringBootApplication
public class Job4jOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Job4jOrderApplication.class, args);
        out.println("Order app run");
    }

}
