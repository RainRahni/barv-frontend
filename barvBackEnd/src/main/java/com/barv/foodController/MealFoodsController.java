package com.barv.foodController;

import com.barv.foodService.MealFoodsServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/mealFoods" )
public class MealFoodsController {
    private MealFoodsServiceImpl mealFoodsServiceImpl;
}
