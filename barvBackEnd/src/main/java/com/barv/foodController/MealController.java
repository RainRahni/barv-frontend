package com.barv.foodController;

import com.barv.exception.FoodAlreadyInDatabaseException;
import com.barv.exception.MealNotFoundException;
import com.barv.foodService.MealServiceImpl;
import com.barv.meals.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    @CrossOrigin(origins = "http://127.0.0.1:5500/", originPatterns = "https://rainrahni.github.io/barv/")
    @PostMapping("/addMeal")
    public Meal addMeal(@RequestBody Meal meal) throws FoodAlreadyInDatabaseException {
        return mealServiceImpl.addMeal(meal);
    }
    @CrossOrigin(origins = "http://127.0.0.1:5500", originPatterns = "https://rainrahni.github.io/barv/")
    @GetMapping("/mealtime={mealTime}")
    public List<String> getExistingNextMealNames(@PathVariable("mealTime") String mealTime) {
        return mealServiceImpl.getExistingNextMealNames(mealTime);
    }
    @CrossOrigin(origins = "http://127.0.0.1:5500/", originPatterns = "http://192.168.1.166:5500"
    )
    @GetMapping("/name={mealName}")
    public Meal getMealWithGivenName(@PathVariable("mealName") String mealName) throws MealNotFoundException {
        return mealServiceImpl.getMealWithGivenName(mealName);
    }
}
