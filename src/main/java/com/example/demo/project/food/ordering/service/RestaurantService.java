package com.example.demo.project.food.ordering.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.project.food.ordering.model.MenuItem;
import com.example.demo.project.food.ordering.model.Restaurant;
import com.example.demo.project.food.ordering.repository.copy.RestaurantRepository;

import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant updateMenu(Long restaurantId, Restaurant restaurant) {
        Restaurant existingRestaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        if (restaurant.getName() != null) {
            existingRestaurant.setName(restaurant.getName());
        }
        if (restaurant.getRating() != null) {
            existingRestaurant.setRating(restaurant.getRating());
        }
        if (restaurant.getMaxCapacity() != null) {
            existingRestaurant.setMaxCapacity(restaurant.getMaxCapacity());
        }
        if (restaurant.getMenuItems() != null) {
            existingRestaurant.getMenuItems().clear(); // Remove old items
            for (MenuItem menuItem : restaurant.getMenuItems()) {
                existingRestaurant.addMenuItem(menuItem);
            }
        }

        return restaurantRepository.save(existingRestaurant);
    }

    public Optional<Restaurant> getRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}
