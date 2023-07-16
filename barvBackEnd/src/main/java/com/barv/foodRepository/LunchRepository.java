package com.barv.foodRepository;

import com.barv.meals.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LunchRepository extends JpaRepository<Meal, Long> {
}
