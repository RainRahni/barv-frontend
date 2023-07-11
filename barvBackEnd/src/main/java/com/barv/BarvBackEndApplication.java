package com.barv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
