package com.barv.foodService;

import com.barv.exception.FoodAlreadyInDatabaseException;
import com.barv.meals.Meal;
import org.springframework.stereotype.Service;

@Service
public interface MealService {
    Meal addMeal(Meal meal) throws FoodAlreadyInDatabaseException;
}
