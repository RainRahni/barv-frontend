package com.barv.foodController;

import com.barv.exception.FoodAlreadyInDatabaseException;
import com.barv.exception.MealNotFoundException;
import com.barv.foodService.MealServiceImpl;
import com.barv.meals.Meal;
import com.barv.meals.MealUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/meal" )
public class MealController {
    @Autowired
    private final MealServiceImpl mealServiceImpl;
    @Autowired
    public MealController(MealServiceImpl mealServiceImpl) { this.mealServiceImpl = mealServiceImpl; }
    @PostMapping("/addMeal")
    public Meal addMeal(@RequestBody Meal meal) throws FoodAlreadyInDatabaseException {
        return mealServiceImpl.addMeal(meal);
    }

    @GetMapping("/mealtime={mealTime}")
    public List<String> getExistingNextMealNames(@PathVariable("mealTime") String mealTime) {
        return mealServiceImpl.getExistingNextMealNames(mealTime);
    }

    @GetMapping("/name={mealName}")
    public Meal getMealWithGivenName(@PathVariable("mealName") String mealName) throws MealNotFoundException {
        return mealServiceImpl.getMealWithGivenName(mealName);
    }

    @PutMapping("/updateMeal")
    public Meal updateExistingMeal(@RequestBody MealUpdateRequest updateRequest) throws FoodAlreadyInDatabaseException {
        Meal currentMeal = updateRequest.getCurrentMeal();
        Meal newMeal = updateRequest.getNewMeal();
        return mealServiceImpl.updateExistingMeal(currentMeal, newMeal);
    }
}
