package com.barv.meals;

import com.barv.food.Food;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Column(name = "id")
    @SequenceGenerator(
            name = "meal_foods_sequence",
            sequenceName = "meal_foods_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "meal_foods_sequence"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "foods_id")
    private Food food;
    private String name;
    private Integer weight;
}
