package com.barv.foodService;

import com.barv.exception.FoodAlreadyInDatabaseException;
import com.barv.exception.FoodNotFoundException;
import com.barv.food.Food;
import com.barv.foodRepository.FoodRepository;
import com.barv.foodService.FoodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FoodServiceTest {
    @Autowired
    private FoodService foodService;
    @MockBean
    private FoodRepository foodRepository;
    private Food food;
    @BeforeEach
    void setUp() throws FoodNotFoundException {
        food = Food.builder()
                .name("awd")
                .protein(12)
                .calories(12)
                .fats(12)
                .weightInGrams(12)
                .carbohydrates(15)
                .Id(1L)
                .build();
        Mockito.when(foodRepository.findById(1L)).thenReturn(Optional.of(food));
    }

    @Test
    public void whenValidFoodId_thenFoodFound() throws FoodNotFoundException {
        Long expectedId = 1L;
        Long actualId =
                foodService.getFoodById(expectedId).getId();
        assertEquals(expectedId, actualId);
    }
    @Test
    public void whenFoodIdNotValid_thenThrowsException() throws FoodNotFoundException {
        Long expectedId = 100L;
        Exception exception = assertThrows(FoodNotFoundException.class, () -> foodService.getFoodById(expectedId));
        String expectedMessage = "No food with this Id in the database!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}