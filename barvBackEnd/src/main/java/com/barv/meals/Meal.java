package com.barv.meals;

import ch.qos.logback.core.model.NamedModel;
import com.barv.food.Food;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Meal {
    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "meal_sequence",
            sequenceName = "meal_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "meal_sequence"
    )
    @EqualsAndHashCode.Exclude private Long mealId;
    private String name;
    private double calories;
    private double protein;
    private double carbohydrates;
    private double fats;
    @Enumerated(EnumType.STRING)
    private MealType type;
    @OneToMany
    private List<Food> foods;
    public void addFoodsToList(List<Food> foodsToAdd) {
        for (Food food: foodsToAdd) {
            if (!foods.contains(food)) {
                foods.add(food);
            }
        }
    }
}
