package com.barv.food;

import com.barv.firebase.FirebaseService;
import com.barv.firebase.FoodFB;
import jakarta.transaction.Transactional;
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
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "api/v1/food" )
public class FoodController {
    private final FoodService foodService;

    /**
     * Constructor for food controller.
     * @param foodService where will all the methods be at.
     */
    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    /**
     * Get food with certain id.
     * @param foodId id which food to
     */
    @GetMapping(path = "/{foodId}")
    public Optional<Food> getFoodWithId(@PathVariable("foodId") Long foodId) {
        return foodService.getFoodById(foodId);

    }

    /**
     * Get all foods in database.
     * @return list of foods in database.
     */
    @GetMapping(path = "/allFoods")
    public List<Food> getAllFoods() {
        return foodService.findAll();
    }

    /**
     * Add given food to database.
     * @param food that will be added to database.
     * @return whether adding was successful or not.
     */
    @PostMapping("/addFood")
    public String addNewFood(@RequestBody Food food) {
        return foodService.addFood(food);
    }

    /**
     * Delete food with given id from database.
     * @param foodId id of the food to be removed from the database.
     * @return whether deleting was successful or not.
     */
    @DeleteMapping(path = "/del{foodId}")
    public String deleteFood(@PathVariable("foodId") Long foodId) {
        return foodService.removeFood(foodId);
    }

    /**
     * Update existing food with given id, with given food.
     * @param foodId id of the food that will be updated.
     * @param food food whose attributes are set to existing food.
     * @return whether updating was successful or not.
     */
    @PutMapping(path = "/upt{foodId}")
    public String updateFoodDetails(@PathVariable("foodId") Long foodId,
                           @RequestBody Food food) {
        return foodService.updateFood(foodId, food);
    }
}
