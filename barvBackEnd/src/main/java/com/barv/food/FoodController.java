package com.barv.food;

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


import java.util.List;

@RestController
@RequestMapping(path = "api/v1/food" )
public class FoodController {
    private final FoodService foodService;

    @Autowired //foodService will be automatically instantiated for us.
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }
    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }
    @PostMapping
    public void registerNewFood(@RequestBody Food food) {
        foodService.addNewFood(food);
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
