package com.example.demo.project.food.ordering;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.project.food.ordering.exception.copy.CustomException;
import com.example.demo.project.food.ordering.model.Orders;
import com.example.demo.project.food.ordering.model.Restaurant;
import com.example.demo.project.food.ordering.repository.copy.OrderRepository;
import com.example.demo.project.food.ordering.repository.copy.RestaurantRepository;
import com.example.demo.project.food.ordering.service.OrderService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    public OrderServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceOrder_Success() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setMaxCapacity(10);

        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
        when(orderRepository.count()).thenReturn(0L); // No current orders

        Orders order = orderService.placeOrder(1L, 1L, 2);

        assertNotNull(order);
        assertEquals("Pending", order.getStatus());
    }

    @Test
    void testPlaceOrder_CapacityExceeded() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setMaxCapacity(1); // Set capacity to 1

        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
        when(orderRepository.count()).thenReturn(1L); // One current order

        CustomException thrown = assertThrows(CustomException.class, () -> {
            orderService.placeOrder(1L, 1L, 1);
        });

        assertEquals("Restaurant cannot process this order due to capacity", thrown.getMessage());
    }
}
