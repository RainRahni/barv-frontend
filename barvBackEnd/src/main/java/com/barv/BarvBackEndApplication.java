package com.barv;

import com.barv.food.Food;
import com.barv.food.FoodRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BarvBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarvBackEndApplication.class, args);
	}
	/**@Bean
	ApplicationRunner applicationRunner(FoodRepository foodRepository) {
		return args -> {
				Food superFoodTwo = new Food(
						"b",
						2,
						2,
						2,
						2,
						2
				);

				foodRepository.save(superFoodTwo);
			};
		};*/
}
