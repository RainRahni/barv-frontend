package com.barv.foodService;

import com.barv.food.Food;
import com.barv.foodRepository.FoodRepository;
import jakarta.transaction.Transactional;
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
public class FoodServiceImpl implements FoodService{
    private final FoodRepository foodRepository;

    /**
     * Constructor for Food Service.
     * @param foodRepository where data is stored.
     */

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }
    public Optional<Food> getFoodById(Long foodId) {
        return foodRepository.findById(foodId);
    }
    /**
     * Add given food to database.
     * @param food to be added to database.
     * @return whether adding to database was successful.
     */
    public String addFood(Food food) {
        if (foodRepository.findAll().contains(food)) {
            return "Food already in database.";
        }
        foodRepository.save(food);
        return food.getName() + " successfully added to database!";
    }
    public void createTable(String tableName, String column1, String column2) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "myusername";
        String password = "mypassword";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
                + " id int NOT NULL AUTO_INCREMENT,\n"
                + " " + column1 + " varchar(255),\n"
                + " " + column2 + " varchar(255),\n"
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
    public String removeFood(Long foodId) {
        boolean exists = foodRepository.existsById(foodId);
        if (!exists) {
            throw new IllegalStateException("Food with given id does not exist!");
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
    public String updateFood(Long foodId, Food food) {
        Food existingFood = foodRepository
                .findById(foodId)
                .orElseThrow(() -> new IllegalStateException("No food with given id"));
        if (!Objects.equals(food, existingFood)) {
            existingFood.setName(food.getName());
            existingFood.setProtein(food.getProtein());
            existingFood.setFats(food.getFats());
            existingFood.setWeight(food.getWeight());
            existingFood.setCarbohydrates(food.getCarbohydrates());
            existingFood.setCalories(food.getCalories());
            return "Successfully changed food`s details!";
        }
        return "Both food`s details are completely same.";
    }
}
