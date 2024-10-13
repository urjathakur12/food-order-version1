package com.example.demo.project.food.ordering.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.project.food.ordering.exception.copy.CustomException;
import com.example.demo.project.food.ordering.model.Orders;
import com.example.demo.project.food.ordering.model.Restaurant;
import com.example.demo.project.food.ordering.repository.copy.OrderRepository;
import com.example.demo.project.food.ordering.repository.copy.RestaurantRepository;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Orders placeOrder(Long restaurantId, Long itemId, int quantity) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new CustomException("Restaurant not found"));
        
        // Check if restaurant can process more items
        int currentOrderCount = (int) orderRepository.count(); // Fetch current orders count
        if (currentOrderCount + quantity > restaurant.getMaxCapacity()) {
            throw new CustomException("Restaurant cannot process this order due to capacity");
        }

        Orders order = new Orders();
        order.setRestaurant(restaurant);
        order.setQuantity(quantity);
        order.setStatus("Pending");
        return orderRepository.save(order);
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }
}
