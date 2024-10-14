package com.example.demo.project.food.ordering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.project.food.ordering.config.copy.ConcurrencyDemo;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class FoodOrderingApplication {

	  
    public static void main(String[] args) {
        SpringApplication.run(FoodOrderingApplication.class, args);
    }
    
}
