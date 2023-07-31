package com.barv.foodController;

import com.barv.exception.FoodAlreadyInDatabaseException;
import com.barv.foodService.MealServiceImpl;
import com.barv.meals.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/meal" )
public class MealController {
    @Autowired
    private final MealServiceImpl mealServiceImpl;
    @Autowired
    public MealController(MealServiceImpl mealServiceImpl) { this.mealServiceImpl = mealServiceImpl; }
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/addMeal")
    public Meal addMeal(@RequestBody Meal meal) throws FoodAlreadyInDatabaseException {
        return mealServiceImpl.addMeal(meal);
    }
}
