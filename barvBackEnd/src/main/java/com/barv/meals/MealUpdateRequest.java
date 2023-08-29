package com.barv.meals;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealUpdateRequest {
    private Meal newMeal;
    private Meal currentMeal;
}
