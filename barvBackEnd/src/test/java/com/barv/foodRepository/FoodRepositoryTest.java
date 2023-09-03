package com.barv.foodRepository;

import com.barv.food.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class FoodRepositoryTest {
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private TestEntityManager entityManager;
    @BeforeEach
    void setUp() {
        Food food =
            Food.builder()
                    .name("Kiivi")
                    .protein(12)
                    .fats(13)
                    .carbohydrates(15)
                    .calories(100)
                    .weightInGrams(100)
                    .build();

        entityManager.persist(food);
    }

    @Test
    public void whenValidId_thenFoodFound() {
        Food food = foodRepository.findById(1L).get();
        assertEquals(food.getName(), "Kiivi");
    }
}