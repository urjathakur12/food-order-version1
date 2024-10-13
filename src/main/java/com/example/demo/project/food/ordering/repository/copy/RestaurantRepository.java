package com.example.demo.project.food.ordering.repository.copy;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.project.food.ordering.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
