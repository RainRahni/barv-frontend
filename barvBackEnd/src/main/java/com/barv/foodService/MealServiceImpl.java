package com.barv.foodService;

import com.barv.exception.FoodAlreadyInDatabaseException;
import com.barv.food.Food;
import com.barv.foodRepository.FoodRepository;
import com.barv.foodRepository.MealRepository;
import com.barv.meals.Meal;
import com.barv.meals.MealFoods;
import com.barv.meals.MealType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        mealRepository.save(meal);

        for (Food food : foodsList) {
            Optional<Food> foodInDatabase = foodRepository.findByNameAndWeight(food.getName(), food.getWeight());
            if (foodInDatabase.isEmpty()) {
                foodService.addFood(food);
            }
            MealFoods mealFoods = new MealFoods();
            mealFoods.setMeal(meal);
            mealFoods.setFood(food);
            mealFoods.setWeight(food.getWeight());
            mealFoodsService.addMealFood(mealFoods);
        }
        return meal;
    }
}
