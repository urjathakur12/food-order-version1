package com.example.demo.project.food.ordering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.project.food.ordering.config.copy.ConcurrencyDemo;

@Component
public class DemoRunner implements CommandLineRunner {
    @Autowired
    private ConcurrencyDemo concurrencyDemo;

    @Override
    public void run(String... args) {
 
       // concurrencyDemo.demoConcurrency();
        //concurrencyDemo.demoConcurrencyFailed();
  
    }
}
