package ru.job4j.job4j_order.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.job4j_order.service.DishService;

@Controller
@AllArgsConstructor
public class OrderViewController {
    private final DishService dishService;

    @GetMapping("/create")
    public String viewCreateOrder(Model model) {
        model.addAttribute("dishes", dishService.getAllDishes());
        return "createorder";
    }

}
