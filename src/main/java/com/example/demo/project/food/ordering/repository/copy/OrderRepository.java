package com.example.demo.project.food.ordering.repository.copy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.project.food.ordering.model.Orders;


public interface OrderRepository extends JpaRepository<Orders, Long> {
}
