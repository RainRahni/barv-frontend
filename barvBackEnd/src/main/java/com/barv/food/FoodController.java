package com.barv.food;

import com.barv.firebase.FirebaseService;
import com.barv.firebase.FoodFB;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "api/v1/food" )
public class FoodController {
    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }
    @GetMapping(path = "/{foodId}")
    public void getFoodWithId(@PathVariable("foodId") Integer foodId)
            throws ExecutionException, InterruptedException {

    }
    @GetMapping(path = "/allFoods")
    public List<Food> getAllFoods() throws ExecutionException, InterruptedException {
        return foodService.findAll();
    }
    @PostMapping("/addFood")
    public String addNewFood(@RequestBody Food food) throws ExecutionException, InterruptedException {
        return foodService.addFood(food);
    }
    @DeleteMapping(path = "/del{foodId}")
    public String deleteFood(@PathVariable("foodId") Long foodId) throws ExecutionException, InterruptedException {
        return foodService.removeFood(foodId);
    }
    /**@PutMapping(path = "{foodId}")
    public void updateFood(@PathVariable("foodId") Long foodId,
                           @RequestParam(required = false) String name) {
        foodService.updateFood(foodId, name);
    }*/
}
