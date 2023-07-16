package com.barv.meals;

import com.barv.food.Food;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meal {
    @Id
    private Long mealId;
    private String name;
    private double calories;
    private double protein;
    private double carbohydrates;
    private double fats;
    private MealType type;
    @OneToMany
    private List<Food> foods;

}
