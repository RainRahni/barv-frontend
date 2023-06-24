package com.barv.food;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @SequenceGenerator(
            name = "food_sequence",
            sequenceName = "food_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "food_sequence"
    )
    private Long Id;
    private String name;
    private int carbohydrates;
    private int protein;
    private int fats;
    private int calories;
    //Weight in grams
    private int weight;
    public Food(String name, int carbs, int protein, int fats, int calories,
                int weight) {
        this.name = name;
        this.carbohydrates = carbs;
        this.protein = protein;
        this.fats = fats;
        this.calories = calories;
        this.weight = weight;
    }
}
