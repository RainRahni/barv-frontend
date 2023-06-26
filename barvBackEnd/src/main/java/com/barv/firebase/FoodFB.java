package com.barv.firebase;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodFB {
    private String id;
    private String name;
    private int carbohydrates;
    private int protein;
    private int fats;
    private int calories;
    //Weight in grams
    private int weight;
    public FoodFB(String name, int carbs, int protein, int fats, int calories,
                  int weight) {
        this.name = name;
        this.carbohydrates = carbs;
        this.protein = protein;
        this.fats = fats;
        this.calories = calories;
        this.weight = weight;
    }
}
