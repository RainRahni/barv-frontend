package com.barv.food;

import com.barv.firebase.FirebaseService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "api/v1/food" )
public class FoodController {
    private final FoodService foodService;
    @Autowired
    private FirebaseService firebaseService;

    @Autowired //foodService will be automatically instantiated for us.
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }
    @GetMapping
    public List<Food> getAllFoods() throws ExecutionException, InterruptedException {
        return foodService.getAllFoods();
    }
    @PostMapping("/registerFood")
    public void registerNewFood(@RequestBody Food food) throws ExecutionException, InterruptedException {
        firebaseService.saveFoodDetails(food);
    }
    @DeleteMapping(path = "{foodId}")
    public void removeFood(@PathVariable("foodId") Long foodId) {
        foodService.deleteFood(foodId);
    }
    @PutMapping(path = "{foodId}")
    public void updateFood(@PathVariable("foodId") Long foodId,
                           @RequestParam(required = false) String name) {
        foodService.updateFood(foodId, name);
    }
}
