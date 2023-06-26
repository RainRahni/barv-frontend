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
import java.util.Optional;
import java.util.concurrent.ExecutionException;
@Service
@Transactional
public class FoodService {
    private final FoodRepository foodRepository;

    /**
     * Constructor for Food Service.
     * @param foodRepository where data is stored.
     */

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }
    public Optional<Food> getFoodById(Long foodId) {
        return foodRepository.findById(foodId);
    }
    /**
     * Add given food to database.
     * @param food to be added to database.
     * @return whether adding to database was successful.
     */
    public String addFood(Food food) {
        if (foodRepository.findAll().contains(food)) {
            return "Food already in database.";
        }
        foodRepository.save(food);
        return food.getName() + " successfully added to database!";
    }

    /**
     * Find all items in database.
     * @return list of foods in database.
     */
    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    /**
     * Remove food with given id from the database.
     * @param foodId food`s id that should be removed.
     * @return whether deleting from database was successful.
     */
    public String removeFood(Long foodId) {
        boolean exists = foodRepository.existsById(foodId);
        if (!exists) {
            throw new IllegalStateException("Food with given id does not exist!");
        }
        foodRepository.deleteById(foodId);
        return String.format("Food with id %d successfully removed from the database!", foodId);
    }
    /**
     * Update food with given id with given food`s attributes.
     * @param foodId what food will be modified.
     * @param food what attributes will be given to existing food.
     * @return whether updating was successful.
     */

    @Transactional
    public String updateFood(Long foodId, Food food) {
        Food existingFood = foodRepository
                .findById(foodId)
                .orElseThrow(() -> new IllegalStateException("No food with given id"));
        if (!Objects.equals(food, existingFood)) {
            existingFood.setName(food.getName());
            existingFood.setProtein(food.getProtein());
            existingFood.setFats(food.getFats());
            existingFood.setWeight(food.getWeight());
            existingFood.setCarbohydrates(food.getCarbohydrates());
            existingFood.setCalories(food.getCalories());
            return "Successfully changed food`s details!";
        }
        return "Both food`s details are completely same.";
    }
}
