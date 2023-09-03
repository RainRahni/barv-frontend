package com.barv.foodService;

import com.barv.food.Food;
import com.barv.foodRepository.MealRepository;
import com.barv.meals.Meal;
import com.barv.meals.MealFoods;
import com.barv.meals.MealType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MealServiceTest {
    @Autowired
    private MealService mealService;
    @MockBean
    private MealRepository mealRepository;
    private Meal meal;
    private Food foodOne;
    private Food foodTwo;
    private MealFoods mealFoods;


    @BeforeEach
    void setUp() {
        foodOne = Food.builder()
                .protein(2)
                .fats(2)
                .name("testOne")
                .carbohydrates(12)
                .weightInGrams(9)
                .calories(200)
                .Id(1L)
                .build();
        foodTwo = Food.builder()
                .protein(2)
                .fats(2)
                .name("testTwo")
                .carbohydrates(12)
                .weightInGrams(9)
                .calories(200)
                .Id(2L)
                .build();
        meal = Meal.builder()
                .mealId(1L)
                .foods(List.of(foodOne, foodTwo))
                .calories(400)
                .carbohydrates(24)
                .fats(4)
                .type(MealType.BREAKFAST)
                .protein(4)
                .name("Breakfast-400")
                .build();
    }

    @Test
    void addMeal() {
    }
}