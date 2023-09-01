package com.barv.foodController;

import com.barv.exception.FoodAlreadyInDatabaseException;
import com.barv.exception.FoodNotFoundException;
import com.barv.food.Food;
import com.barv.foodService.FoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/food" )
public class FoodController {
    private final FoodServiceImpl foodService;

    /**
     * Constructor for food controller.
     * @param foodService where will all the methods be at.
     */
    @Autowired
    public FoodController(FoodServiceImpl foodService) {
        this.foodService = foodService;
    }

    /**
     * Get food with certain id.
     * @param foodId id which food to
     */
    @GetMapping(path = "/{foodId}")
    public Food getFoodWithId(@PathVariable("foodId") Long foodId) throws FoodNotFoundException {
        return foodService.getFoodById(foodId);
    }

    /**
     * Get all foods in database.
     * @return list of foods in database.
     */
    @GetMapping(path = "/allFoods")
    public List<Food> getAllFoods() {
        return foodService.findAllFoods();
    }

    /**
     * Add given food to database.
     * @param food that will be added to database.
     * @return whether adding was successful or not.
     */
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/addFood")
    public Food addNewFood(@RequestBody Food food) throws FoodAlreadyInDatabaseException {
        return foodService.addFood(food);
    }

    /**
     * Delete food with given id from database.
     * @param foodId id of the food to be removed from the database.
     * @return whether deleting was successful or not.
     */
    @DeleteMapping(path = "/del{foodId}")
    public String deleteFood(@PathVariable("foodId") Long foodId) throws FoodNotFoundException {
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
                           @RequestBody Food food) throws FoodAlreadyInDatabaseException, FoodNotFoundException {
        return foodService.updateFood(foodId, food);
    }
}
