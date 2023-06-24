package com.barv.food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface FoodRepository
        extends JpaRepository<Food, Long> {
    //SELECT * FROM food WHERE protein = ?
    @Query("SELECT f FROM Food f where f.protein = 2")
    Optional<Food> findFoodByProteinAmount(int protein);
}
