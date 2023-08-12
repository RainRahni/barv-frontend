package com.barv.foodService;

import com.barv.exception.FoodAlreadyInDatabaseException;
import com.barv.exception.FoodNotFoundException;
import com.barv.food.Food;
import com.barv.foodRepository.FoodRepository;
import com.barv.meals.Meal;
import com.barv.meals.MealType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class FoodServiceImpl implements FoodService {
    @Autowired
    private final FoodRepository foodRepository;

    /**
     * Constructor for Food Service.
     * @param foodRepository where data is stored.
     */

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }
    public Food getFoodById(Long foodId) throws FoodNotFoundException {
        Optional<Food> potentialFood = foodRepository.findById(foodId);
        if (potentialFood.isEmpty()) {
            throw new FoodNotFoundException("No food with this Id in the database!");
        }
        return potentialFood.get();
    }
    /**
     * Add given food to database.
     * @param food to be added to database.
     * @return whether adding to database was successful.
     */
    public Food addFood(Food food) throws FoodAlreadyInDatabaseException {
        if (foodRepository.findAll().contains(food)) {
            throw new FoodAlreadyInDatabaseException("Food is already in the database!");
        }
        foodRepository.save(food);
        return food;
    }

    /**
     * Create new table into the database.
     * @param tableName where is meal type and how many calories.
     * @param mealType that determines into which ...
     */
    public void createTable(String tableName, MealType mealType) {
        String url = "jdbc:postgresql://barv-hornet-8737.8nj.cockroachlabs.cloud:26257/defaultdb";
        String username = "rainrhni";
        String password = "1PPDl4WIKqOnWCtShGoRqg";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
                + " id int NOT NULL AUTO_INCREMENT,\n"
                + " " + "calories" + " varchar(255),\n"
                + " " + "protein" + " varchar(255),\n"
                + " " + "carbohydrates" + " varchar(255),\n"
                + " " + "fats" + " varchar(255),\n"
                + " " + mealType + " varchar(255),\n"
                + " PRIMARY KEY (id)\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Find all items in database.
     * @return list of foods in database.
     */
    public List<Food> findAllFoods() {
        return foodRepository.findAll();
    }

    /**
     * Remove food with given id from the database.
     * @param foodId food`s id that should be removed.
     * @return whether deleting from database was successful.
     */
    public String removeFood(Long foodId) throws FoodNotFoundException {
        boolean exists = foodRepository.existsById(foodId);
        if (!exists) {
            throw new FoodNotFoundException("Food with given id does not exist!");
        }
        foodRepository.deleteById(foodId);
        return String.format("Food with id %d successfully removed from the database!", foodId);
    }
    /**
     * Update food with given id with given food`s attributes.
     * @param foodId what food will be modified.
     * @param food what attributes will be given to existing food.
     * @return whether updating was successful.
     */

    @Transactional
    public String updateFood(Long foodId, Food food) throws FoodNotFoundException, FoodAlreadyInDatabaseException {
        Food existingFood = foodRepository
                .findById(foodId)
                .orElseThrow(FoodNotFoundException::new);
        if (!Objects.equals(food, existingFood)) {
            existingFood.setName(food.getName());
            existingFood.setProtein(food.getProtein());
            existingFood.setFats(food.getFats());
            existingFood.setWeightInGrams(food.getWeightInGrams());
            existingFood.setCarbohydrates(food.getCarbohydrates());
            existingFood.setCalories(food.getCalories());
            return "Successfully changed food`s details!";
        }
        throw new FoodAlreadyInDatabaseException("Food objects are equal already!");
    }
}
