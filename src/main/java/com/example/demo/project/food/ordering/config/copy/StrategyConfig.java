package com.example.demo.project.food.ordering.config.copy;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.project.food.ordering.service.HighestRatingStrategy;
import com.example.demo.project.food.ordering.service.LowestPriceStrategy;
import com.example.demo.project.food.ordering.service.RestaurantSelectionStrategy;

@Configuration
public class StrategyConfig {

    @Bean
    public RestaurantSelectionStrategy lowestPriceStrategy() {
        return new LowestPriceStrategy();
    }

    @Bean
    public RestaurantSelectionStrategy highestRatingStrategy() {
        return new HighestRatingStrategy();
    }
}
