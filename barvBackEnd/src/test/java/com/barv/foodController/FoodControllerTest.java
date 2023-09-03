package com.barv.foodController;

import com.barv.exception.FoodAlreadyInDatabaseException;
import com.barv.exception.FoodNotFoundException;
import com.barv.food.Food;
import com.barv.foodService.FoodService;
import com.barv.foodService.FoodServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FoodController.class)
class FoodControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Food food;
    @MockBean
    private FoodServiceImpl foodService;

    @BeforeEach
    void setUp() {
        food = Food.builder()
                .name("awd")
                .protein(12)
                .calories(12)
                .fats(12)
                .weightInGrams(12)
                .carbohydrates(15)
                .Id(1L)
                .build();
    }
    @Test
    void whenFoodNotInDatabase_thenAddFoodToDatabase() throws Exception {
        Food inputFood = Food.builder()
                .name("awd")
                .protein(12)
                .calories(12)
                .fats(12)
                .weightInGrams(12)
                .carbohydrates(15)
                .build();
        Mockito.when(foodService.addFood(inputFood)).thenReturn(food);

        mockMvc.perform(post("/api/v1/food/addFood")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\": \"awd\",\n" +
                        "    \"protein\": 12,\n" +
                        "    \"weight\": 12,\n" +
                        "    \"carbohydrates\": 15,\n" +
                        "    \"fats\": 12,\n" +
                        "    \"calories\": 12\n" +
                        "}"))
                .andExpect(status().isOk());
    }
    @Test
    public void whenFoodInDatabase_thenThrowException() throws Exception {
        Food inputFood = Food.builder()
                .name("awd")
                .protein(12)
                .calories(12)
                .fats(12)
                .weightInGrams(12)
                .carbohydrates(15)
                .build();
        Mockito.doThrow(new FoodAlreadyInDatabaseException()).when(foodService).addFood(inputFood);
    }
    @Test
    public void whenFoodWithIdValid_thenFound() throws Exception {
        Mockito.when(foodService.getFoodById(1L)).thenReturn(food);
        mockMvc.perform(get("/api/v1/food/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(food.getId()));
    }
    @Test
    public void whenFoodWithInvalidId_thenNotFound() throws Exception {
        Mockito.when(foodService.getFoodById(100L)).thenThrow(FoodNotFoundException.class);
        mockMvc.perform(get("/api/v1/food/100").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void whenFoodWithValidId_thenDeleteFood() throws Exception {
        Mockito.when(foodService.removeFood(1L))
                .thenReturn("Food with id 1 successfully removed from the database!");
        mockMvc.perform(delete("/api/v1/food/del1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    public void whenFoodWithInvalidId_thenThrowsNoFoodException() throws Exception {
        Mockito.when(foodService.removeFood(100L)).thenThrow(FoodNotFoundException.class);
        mockMvc.perform(delete("/api/v1/food/del100").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof FoodNotFoundException));
    }
    @Test
    public void whenUpdatingFoodWithInvalidId_thenThrowsNoFoodException() throws Exception {
        Food inputFood = Food.builder()
                .name("howdy")
                .protein(12)
                .calories(12)
                .fats(12)
                .weightInGrams(12)
                .carbohydrates(15)
                .build();
        Mockito.when(foodService.updateFood(100L, inputFood)).thenThrow(FoodNotFoundException.class);
        mockMvc.perform(put("/upt100").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void whenUpdatingFoodWithExactSameObject_thenThrowFoodAlreadyExistsException()
            throws Exception {
        Food inputFood = Food.builder()
                .name("awd")
                .protein(12)
                .calories(12)
                .fats(12)
                .weightInGrams(12)
                .carbohydrates(15)
                .build();
        Mockito.when(foodService.updateFood(1L, inputFood)).thenThrow(FoodAlreadyInDatabaseException.class);
        mockMvc.perform(put("/upt1").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"name\": \"awd\",\n" +
                "    \"protein\": 12,\n" +
                "    \"weight\": 12,\n" +
                "    \"carbohydrates\": 15,\n" +
                "    \"fats\": 12,\n" +
                "    \"calories\": 12\n" +
                "}"))
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void whenUpdatingFoodWithValidObject_thenChangeDetails()
            throws Exception {
        Food inputFood = Food.builder()
                .name("adt")
                .protein(12)
                .calories(12)
                .fats(12)
                .weightInGrams(12)
                .carbohydrates(15)
                .build();
        Mockito.when(foodService.updateFood(1L, inputFood)).thenReturn("Successfully changed food`s details!");
        mockMvc.perform(put("/upt1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"howdyMate\",\n" +
                                "    \"protein\": 12,\n" +
                                "    \"weight\": 12,\n" +
                                "    \"carbohydrates\": 15,\n" +
                                "    \"fats\": 12,\n" +
                                "    \"calories\": 12\n" +
                                "}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("howdyMate"));
    }
}