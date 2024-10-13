package com.example.demo.project.food.ordering.service;

import com.example.demo.project.food.ordering.model.MenuItem;
import com.example.demo.project.food.ordering.model.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyService {
    
    @Value("${restaurant.selection.strategy}")
    private String strategyBeanName;

    @Autowired
    private ApplicationContext applicationContext;

    public Restaurant selectRestaurant(List<Restaurant> restaurants, MenuItem menuItem) {
        RestaurantSelectionStrategy strategy = (RestaurantSelectionStrategy) applicationContext.getBean(strategyBeanName);
        return strategy.selectRestaurant(restaurants, menuItem);
    }
}
