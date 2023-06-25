package com.barv.firebase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "api/v1/food" )
public class FoodFBController {
    //private final FoodService foodService;
    //@Autowired
    private final FirebaseService firebaseService;

    /**@Autowired //foodService will be automatically instantiated for us.
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }*/
    @Autowired
    public FoodFBController(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }
    @GetMapping(path = "/{collectionId}")
    public List<FoodFB> getFoodsInCollection(@PathVariable("collectionId") String collectionId) throws ExecutionException, InterruptedException {
        return firebaseService.getAllFoodsInCollection(collectionId);
    }
    @GetMapping(path = "/{documentId}")
    public String getFoodWithDocumentId(@PathVariable("documentId") String documentId)
            throws ExecutionException, InterruptedException {
        return firebaseService.getFood(documentId);
    }
    @PostMapping("/registerFood")
    public String registerNewFood(@RequestBody FoodFB foodFB) throws ExecutionException, InterruptedException {
        return firebaseService.saveFoodDetails(foodFB);
    }
    @DeleteMapping(path = "/{foodId}")
    public void removeFood(@PathVariable("foodId") String foodId) throws ExecutionException, InterruptedException {
        firebaseService.deleteFood(foodId);
    }
    /**@PutMapping(path = "{foodId}")
    public void updateFood(@PathVariable("foodId") Long foodId,
                           @RequestParam(required = false) String name) {
        foodService.updateFood(foodId, name);
    }*/
}
