package com.barv.foodService;

import com.barv.meals.Meal;
import org.springframework.stereotype.Service;

@Service
public interface MealService {
    Meal addMeal(Meal meal);
}
