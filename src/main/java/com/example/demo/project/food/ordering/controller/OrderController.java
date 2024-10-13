package com.example.demo.project.food.ordering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.project.food.ordering.model.Orders;
import com.example.demo.project.food.ordering.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public Orders placeOrder(@RequestParam Long restaurantId, @RequestParam Long itemId, @RequestParam int quantity) {
        return orderService.placeOrder(restaurantId, itemId, quantity);
    }

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }
}
