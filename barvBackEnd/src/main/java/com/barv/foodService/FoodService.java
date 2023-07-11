package com.barv.foodService;

import com.barv.food.Food;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FoodService {
    Optional<Food> getFoodById(Long foodId);
    String addFood(Food food);
    List<Food> findAllFoods();
    String removeFood(Long foodId);
    String updateFood(Long foodId, Food food);
}
