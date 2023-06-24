package com.barv.food;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            FoodRepository foodRepository) {
        return args -> {
            Food superFood = new Food(
                    2L,
                    "a",
                    2,
                    4,
                    2,
                    2,
                    2
            );
            Food superFoodTwo = new Food(
                    "b",
                    2,
                    2,
                    2,
                    2,
                    2
            );

            foodRepository.saveAll(List.of(superFood, superFoodTwo));
        };
    }
}
