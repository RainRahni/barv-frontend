package com.barv.foodService;

import com.barv.exception.FoodAlreadyInDatabaseException;
import com.barv.exception.MealNotFoundException;
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
        saveNewFoodsForMeal(meal.getFoods());
        mealRepository.save(meal);
        mealFoodsService.deleteMealFoodsWithNullValues();
        List<Food> foodsList = meal.getFoods();
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

    @Override
    public List<String> getExistingNextMealNames(String mealTime) {
        MealType correspondingMealType = MealType.valueOf(mealTime);
        List<Meal> mealsWithTypeInDatabase =
                mealRepository.findByType(correspondingMealType);
        return mealsWithTypeInDatabase.stream().map(Meal::getName).toList();
    }

    @Override
    public Meal getMealWithGivenName(String mealName) throws MealNotFoundException {
        if (mealRepository.existsByName(mealName)) {
            return mealRepository.findByName(mealName).get(0);
        }
        throw new MealNotFoundException("No meal with given name found!");
    }

    @Override
    public Meal updateExistingMeal(Meal currentMeal, Meal newMeal) throws FoodAlreadyInDatabaseException {
        Meal mealFromDatabase = mealRepository.findById(currentMeal.getMealId()).get();
        if (currentMeal.equals(newMeal)) {
            throw new FoodAlreadyInDatabaseException("Both meals are exactly the same!");
        }
        Meal updatedMeal = updateAttributesOfMeal(mealFromDatabase, newMeal);
        List<Food> foods = newMeal.getFoods();
        saveNewFoodsForMeal(foods);
        updatedMeal.addFoodsToList(foods);
        mealRepository.save(updatedMeal);
        return updatedMeal;
    }

    private Meal updateAttributesOfMeal(Meal currentMeal, Meal newMeal) {
        currentMeal.setName(newMeal.getName());
        currentMeal.setCalories(newMeal.getCalories());
        currentMeal.setCarbohydrates(newMeal.getCarbohydrates());
        currentMeal.setFats(newMeal.getFats());
        currentMeal.setProtein(newMeal.getProtein());
        currentMeal.setType(newMeal.getType());
        return currentMeal;
    }

    private void saveNewFoodsForMeal(List<Food> foodsToSave) throws FoodAlreadyInDatabaseException {
        for (Food food : foodsToSave) {
            Optional<Food> foodInDatabase =
                    foodRepository.findByNameAndWeightInGrams(food.getName(), food.getWeightInGrams());
            if (foodInDatabase.isEmpty()) {
                foodService.addFood(food);
            }
        }
    }

}
