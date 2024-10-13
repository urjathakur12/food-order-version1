package com.example.demo.project.food.ordering.service;


import com.example.demo.project.food.ordering.model.MenuItem;
import com.example.demo.project.food.ordering.model.Restaurant;

import java.util.List;

public interface RestaurantSelectionStrategy {
 Restaurant selectRestaurant(List<Restaurant> restaurants, MenuItem menuItem);
}
