package com.barv.foodService;

import com.barv.exception.FoodAlreadyInDatabaseException;
import com.barv.food.Food;
import com.barv.foodRepository.FoodRepository;
import com.barv.foodRepository.MealRepository;
import com.barv.meals.Meal;
import com.barv.meals.MealFoods;
import com.barv.meals.MealType;
import jakarta.transaction.Transactional;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private final MealRepository mealRepository;
    @Autowired
    private FoodService foodService;
    @Autowired
    private MealFoodsService mealFoodsService;
    @Autowired
    private FoodRepository foodRepository;
    public MealServiceImpl(MealRepository mealRepository) { this.mealRepository = mealRepository; }


    /**
     * Add given meal to the database.
     * @param meal to be added to database.
     * @return meal that was added to database.
     */
    @Override
    public Meal addMeal(Meal meal) throws FoodAlreadyInDatabaseException {
        List<Food> foodsList = meal.getFoods();
        for (Food food : foodsList) {
            Optional<Food> foodInDatabase =
                    foodRepository.findByNameAndWeightInGrams(food.getName(), food.getWeightInGrams());
            if (foodInDatabase.isEmpty()) {
                foodService.addFood(food);
            }
        }
        mealRepository.save(meal);
        mealFoodsService.deleteMealFoodsWithNullValues();
        for (Food food: foodsList) {
            MealFoods mealFoods = new MealFoods();
            mealFoods.setMeal(meal);
            mealFoods.setFood(food);
            mealFoods.setWeight(food.getWeightInGrams());
            mealFoods.setName(food.getName());
            mealFoodsService.addMealFood(mealFoods);
        }
        return meal;
    }
    public Meal updateMeal(Long idOfMealToUpdate, Meal newMealFields) {
        Meal meal = mealRepository.findById(idOfMealToUpdate).get();
        meal.setName(newMealFields.getName());
        meal.setFats(newMealFields.getFats());
        meal.setFoods(newMealFields.getFoods());
        meal.setType(newMealFields.getType());
        meal.setCalories(newMealFields.getCalories());
        meal.setCarbohydrates(newMealFields.getCarbohydrates());
        meal.setProtein(newMealFields.getProtein());
        return meal;
    }

}
