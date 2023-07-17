package com.barv.foodRepository;

import com.barv.meals.MealFoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealFoodsRepository extends JpaRepository<MealFoods, Long> {
}
