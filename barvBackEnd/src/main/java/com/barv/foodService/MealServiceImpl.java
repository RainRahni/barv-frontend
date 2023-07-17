package com.barv.foodService;

import com.barv.foodRepository.MealRepository;
import com.barv.meals.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private final MealRepository mealRepository;
    public MealServiceImpl(MealRepository mealRepository) { this.mealRepository = mealRepository; }

    /**
     * Add given meal to the database.
     * @param meal to be added to database.
     * @return meal that was added to database.
     */
    @Override
    public Meal addMeal(Meal meal) {
        return mealRepository.save(meal);
    }
}
