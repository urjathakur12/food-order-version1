package com.example.demo.project.food.ordering.service;


import com.example.demo.project.food.ordering.exception.copy.CustomException;
import com.example.demo.project.food.ordering.model.MenuItem;
import com.example.demo.project.food.ordering.model.Restaurant;

import java.util.List;

public class LowestPriceStrategy implements RestaurantSelectionStrategy {
 @Override
 public Restaurant selectRestaurant(List<Restaurant> restaurants, MenuItem menuItem) {
     return restaurants.stream()
             .min((r1, r2) -> Double.compare(
                     r1.getMenuItems().stream()
                             .filter(item -> item.getId().equals(menuItem.getId()))
                             .findFirst()
                             .orElseThrow(() -> new CustomException("Menu item not found"))
                             .getPrice(),
                     r2.getMenuItems().stream()
                             .filter(item -> item.getId().equals(menuItem.getId()))
                             .findFirst()
                             .orElseThrow(() -> new CustomException("Menu item not found"))
                             .getPrice()))
             .orElseThrow(() -> new CustomException("No restaurant available for this item"));
 }
}

