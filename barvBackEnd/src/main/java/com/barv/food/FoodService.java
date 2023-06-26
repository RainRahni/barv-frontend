package com.barv.food;

import com.barv.firebase.FirebaseInit;
import com.barv.firebase.FoodFB;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
@Service
@Transactional
public class FoodService {
    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }
    public String addFood(Food food) {
        if (foodRepository.findAll().contains(food)) {
            return "Food already in database.";
        }
        foodRepository.save(food);
        return food.getName() + " successfully added to database!";
    }
    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    public String removeFood(Long foodId) {
        boolean exists = foodRepository.existsById(foodId);
        if (!exists) {
            throw new IllegalStateException("Food with given id does not exist!");
        }
        foodRepository.deleteById(foodId);
        return String.format("Food with id %d successfully removed from the database!", foodId);
    }

    @Transactional
    public String updateFoodName(Long foodId, String name) {
        Food food = foodRepository
                .findById(foodId)
                .orElseThrow(() -> new IllegalStateException("No food with given id"));
        if (name != null && name.length() > 0 && !Objects.equals(food.getName(), name)) {
            food.setName(name);
        }
        return "Successfully changed food`s name to " + name;
    }
}
