package com.barv.food;

import com.barv.firebase.FoodFB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
public interface FoodRepository extends JpaRepository<Food, Long> {
    //SELECT * FROM food WHERE protein = ?
    //Optional<FoodFB> findFoodByProteinAmount(int protein);
}
