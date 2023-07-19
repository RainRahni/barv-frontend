package com.barv.foodService;

import com.barv.exception.FoodAlreadyInDatabaseException;
import com.barv.exception.FoodNotFoundException;
import com.barv.food.Food;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FoodService {
    Food getFoodById(Long foodId) throws FoodNotFoundException;
    Food addFood(Food food) throws FoodAlreadyInDatabaseException;
    List<Food> findAllFoods();
    String removeFood(Long foodId) throws FoodNotFoundException;
    String updateFood(Long foodId, Food food) throws FoodNotFoundException, FoodAlreadyInDatabaseException;
}
