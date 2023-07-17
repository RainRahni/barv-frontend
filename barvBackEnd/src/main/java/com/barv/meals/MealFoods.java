package com.barv.meals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealFoods {
    @Id
    @SequenceGenerator(
            name = "meal_foods_sequence",
            sequenceName = "meal_foods_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "meal_foods_sequence"
    )
    private Long mealFoodsId;
    private Long mealId;
    private Long foodId;
    private int weight;
}
