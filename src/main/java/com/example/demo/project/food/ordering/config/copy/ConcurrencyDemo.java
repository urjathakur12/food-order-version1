package com.example.demo.project.food.ordering.config.copy;

import com.example.demo.project.food.ordering.exception.copy.CustomException;
import com.example.demo.project.food.ordering.model.Orders;
import com.example.demo.project.food.ordering.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ConcurrencyDemo {

    @Autowired
    private OrderService orderService;

    public void demoConcurrency() {
        System.out.println("Starting concurrency demo...");
        ExecutorService executorService = Executors.newFixedThreadPool(10); // Thread pool with 10 threads
        List<Callable<Void>> tasks = new ArrayList<>();

        // Simulate placing orders concurrently
        for (int i = 0; i < 10; i++) {
            final int orderNumber = i; // Capture the current order number
            tasks.add(() -> {
                try {
                    // Each thread tries to place an order for the same restaurant
                    System.out.println("Thread " + orderNumber + " attempting to place order...");
                    orderService.placeOrder(1L, 1L, 1); // Assume restaurant ID 1 and menu item ID 1
                    System.out.println("Thread " + orderNumber + " placed order successfully.");
                } catch (Exception e) {
                    System.out.println("Thread " + orderNumber + " encountered an exception: " + e.getMessage());
                }
                return null;
            });
        }

        // Execute all tasks concurrently
        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        
        System.out.println("Concurrency demo finished.");
    }
    
    
    public void demoConcurrencyFailed() {
    	 ExecutorService executorService = Executors.newFixedThreadPool(2); // Thread pool with 2 threads

         Orders initialOrder = orderService.placeOrder(1L, 1L, 2); // Place an initial order

         // Simulate two threads attempting to update the same order concurrently
         Runnable task1 = () -> {
             try {
                 Orders updatedOrder = initialOrder;
                 updatedOrder.setQuantity(3); // Change quantity for the first update
                 orderService.updateOrder(initialOrder.getId(), updatedOrder);
                 System.out.println("Thread 1 updated order successfully.");
             } catch (CustomException e) {
                 System.out.println("Thread 1 encountered an exception: " + e.getMessage());
             }
         };

         Runnable task2 = () -> {
             try {
                 Orders updatedOrder = initialOrder;
                 updatedOrder.setQuantity(8); // Change quantity for the second update
                 orderService.updateOrder(initialOrder.getId(), updatedOrder);
                 System.out.println("Thread 2 updated order successfully.");
             } catch (CustomException e) {
                 System.out.println("Thread 2 encountered an exception: " + e.getMessage());
             }
         };

         executorService.submit(task1);
         executorService.submit(task2);
         
         executorService.shutdown();
     }
}