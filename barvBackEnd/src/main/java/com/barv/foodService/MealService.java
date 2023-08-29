package com.barv.foodService;

import com.barv.exception.FoodAlreadyInDatabaseException;
import com.barv.exception.MealNotFoundException;
import com.barv.meals.Meal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MealService {
    Meal addMeal(Meal meal) throws FoodAlreadyInDatabaseException;

    List<String> getExistingNextMealNames(String mealTime);

    Meal getMealWithGivenName(String mealName) throws MealNotFoundException;

    Meal updateExistingMeal(Meal currentMeal, Meal newMeal) throws FoodAlreadyInDatabaseException;
}
