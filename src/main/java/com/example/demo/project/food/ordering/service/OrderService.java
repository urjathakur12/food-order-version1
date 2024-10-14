package com.example.demo.project.food.ordering.service;
import com.example.demo.project.food.ordering.exception.copy.CustomException;
import com.example.demo.project.food.ordering.logger.LoggingAspect;
import com.example.demo.project.food.ordering.model.Orders;
import com.example.demo.project.food.ordering.model.Restaurant;
import com.example.demo.project.food.ordering.repository.copy.OrderRepository;
import com.example.demo.project.food.ordering.repository.copy.RestaurantRepository;

import jakarta.persistence.OptimisticLockException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Transactional
    public Orders placeOrder(Long restaurantId, Long menuItemId, int quantity) {
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(restaurantId);
        if (restaurantOpt.isPresent()) {
            Restaurant restaurant = restaurantOpt.get();

            // Check if capacity is available
            if (orderRepository.count() < restaurant.getMaxCapacity()) {
                Orders order = new Orders();
                order.setRestaurant(restaurant);
                order.setQuantity(quantity);
                order.setStatus("Pending");
                return orderRepository.save(order);
            } else {
                throw new CustomException("Restaurant cannot process this order due to capacity");
            }
        } else {
            throw new CustomException("Restaurant not found");
        }
    }
    
 
    public Orders updateOrder(Long orderId, Orders updatedOrder) {
        try {
            return orderRepository.save(updatedOrder);
        } catch (OptimisticLockException e) {
            throw new CustomException("Order version mismatch. Please refresh and try again.");
        }
    }
    
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }
}
