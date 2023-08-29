package com.barv.foodService;

import com.barv.exception.FoodAlreadyInDatabaseException;
import com.barv.foodRepository.MealFoodsRepository;
import com.barv.meals.Meal;
import com.barv.meals.MealFoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealFoodsServiceImpl implements MealFoodsService {
    @Autowired
    private final MealFoodsRepository mealFoodsRepository;
    public MealFoodsServiceImpl(MealFoodsRepository mealFoodsRepository) {
        this.mealFoodsRepository = mealFoodsRepository;
    }
    @Override
    public MealFoods addMealFood(MealFoods mealFoods) throws FoodAlreadyInDatabaseException {
        if (mealFoods.getId() == null || !mealFoodsRepository.existsById(mealFoods.getId())) {
            mealFoodsRepository.save(mealFoods);
        }
        return mealFoods;
    }

    @Override
    public List<MealFoods> deleteMealFoodsWithNullValues() {
        List<MealFoods> mealFoodsWithNullValues = mealFoodsRepository.findMealFoodsByName(null);
        mealFoodsRepository.deleteAll(mealFoodsWithNullValues);
        return mealFoodsWithNullValues;
    }

}
