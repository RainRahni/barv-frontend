package com.barv.foodService;

import com.barv.exception.FoodAlreadyInDatabaseException;
import com.barv.meals.Meal;
import com.barv.meals.MealFoods;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MealFoodsService {
    MealFoods addMealFood(MealFoods mealFoods) throws FoodAlreadyInDatabaseException;
    List<MealFoods> deleteMealFoodsWithNullValues();
}
