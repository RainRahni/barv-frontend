package com.barv.food;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FoodService {
    private final FoodRepository foodRepository;
    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public void addNewFood(Food food) {
        /*Optional<Food> foodByProtein =
            foodRepository.findFoodByProteinAmount(food.getProtein());
        if (foodByProtein.isEmpty()) {
            throw new IllegalStateException("no such protein");
        }*/
        foodRepository.save(food);
    }
    public void deleteFood(Long foodId) {
        boolean exists = foodRepository.existsById(foodId);
        if (!exists) {
            throw new IllegalStateException("student with given id does not exist");
        }

        foodRepository.deleteById(foodId);
    }
    @Transactional
    public void updateFood(Long foodId, String name) {
        Food food = foodRepository
                .findById(foodId)
                .orElseThrow(() -> new IllegalStateException("no food with given id"));
        if (name != null && name.length() > 0 && !Objects.equals(food.getName(), name)) {
            food.setName(name);
        }

    }
}
