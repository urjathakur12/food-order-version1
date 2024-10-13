package com.example.demo.project.food.ordering.service;

import com.example.demo.project.food.ordering.exception.copy.CustomException;
import com.example.demo.project.food.ordering.model.MenuItem;
import com.example.demo.project.food.ordering.model.Restaurant;

import java.util.List;

public class HighestRatingStrategy implements RestaurantSelectionStrategy {
 @Override
 public Restaurant selectRestaurant(List<Restaurant> restaurants, MenuItem menuItem) {
     return restaurants.stream()
             .max((r1, r2) -> Double.compare(r1.getRating(), r2.getRating()))
             .orElseThrow(() -> new CustomException("No restaurant available for this item"));
 }
}
