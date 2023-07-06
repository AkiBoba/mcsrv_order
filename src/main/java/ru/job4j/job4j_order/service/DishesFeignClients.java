package ru.job4j.job4j_order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.job4j_order.model.Dish;

import java.util.List;

@FeignClient(name = "dish", url = "http://localhost:8080/")
public interface DishesFeignClients {
    @RequestMapping(method = RequestMethod.GET, value = "/dish/")
    Dish getDishById(@PathVariable Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/dish/")
    List<Dish> getAll();
}
