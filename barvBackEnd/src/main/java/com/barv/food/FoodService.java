package com.barv.food;

import com.barv.firebase.FirebaseInit;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
public class FoodService {
    private final FoodRepository foodRepository;
    @Autowired
    private FirebaseInit database;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }
    public List<Food> getAllFoods() throws ExecutionException, InterruptedException {
        List<Food> foodList = new ArrayList<>();
        CollectionReference breakfast = database.getFirebase().collection("Breakfast");
        ApiFuture<QuerySnapshot> querySnap = breakfast.get();
        for (DocumentSnapshot doc : querySnap.get().getDocuments()) {
            Food food = doc.toObject(Food.class);
            foodList.add(food);
        }
        return foodList;
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
