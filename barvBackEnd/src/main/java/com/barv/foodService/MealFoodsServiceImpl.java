package com.barv.foodService;

import com.barv.exception.FoodAlreadyInDatabaseException;
import com.barv.foodRepository.MealFoodsRepository;
import com.barv.meals.MealFoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealFoodsServiceImpl implements MealFoodsService {
    @Autowired
    private final MealFoodsRepository mealFoodsRepository;
    public MealFoodsServiceImpl(MealFoodsRepository mealFoodsRepository) {
        this.mealFoodsRepository = mealFoodsRepository;
    }
    @Override
    public MealFoods addMealFood(MealFoods mealFoods) throws FoodAlreadyInDatabaseException {
        if (!mealFoodsRepository.existsById(mealFoods.getMealFoodsId())) {
            mealFoodsRepository.save(mealFoods);
        }
        throw new FoodAlreadyInDatabaseException();
    }
}
