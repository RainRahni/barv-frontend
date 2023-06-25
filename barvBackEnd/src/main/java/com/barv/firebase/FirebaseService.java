package com.barv.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {
    /**
     * Save food to firebase database.
     * @param foodFB to be added to database.
     * @return String of time it took to update.
     * @throws ExecutionException which is thrown when attempting to retrieve result of task that aborted.
     * @throws InterruptedException which is thrown when thread is interrupted either before or during activity.
     */
    public String saveFoodDetails(FoodFB foodFB) throws ExecutionException, InterruptedException {
        //Connect to database.
        Firestore firestoreDatabase = FirestoreClient.getFirestore();
        //Get next document
        DocumentReference newDocRef = firestoreDatabase.collection("Breakfast").document();
        //Set next docs id to food to be added.
        foodFB.setId(newDocRef.getId());
        // Connect to breakfast and do a set
        ApiFuture<WriteResult> collectionsApiFuture =
                firestoreDatabase.collection("Breakfast").document(foodFB.getName()).set(foodFB);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    /**
     * Get food with given id.
     * @param documentId of the food to get.
     * @return String representation of food if found, otherwise that document does not exist.
     * @throws ExecutionException which is thrown when attempting to retrieve result of task that aborted.
     * @throws InterruptedException which is thrown when thread is interrupted either before or during activity.
     */
    public String getFood(String documentId) throws ExecutionException, InterruptedException {
        Firestore firestoreDatabase = FirestoreClient.getFirestore();
        // Get document with id.
        DocumentReference documentReference = firestoreDatabase.collection("Breakfast").document(documentId);
        ApiFuture<DocumentSnapshot> docSnap = documentReference.get();
        DocumentSnapshot document = docSnap.get();
        FoodFB foodFB;
        if (document.exists()) { foodFB = document.toObject(FoodFB.class);  return foodFB.toString(); }
        return "Document does not exist";
    }

    /**
     * Get food by given food id.
     * @param foodId of food to retrieve from database.
     * @return food if food with id is present, otherwise empty optional.
     * @throws ExecutionException which is thrown when attempting to retrieve result of task that aborted.
     * @throws InterruptedException which is thrown when thread is interrupted either before or during activity.
     */
    public Optional<FoodFB> getFoodById(String foodId) throws ExecutionException, InterruptedException {
        Firestore firestoreDatabase = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> docs = firestoreDatabase.collection("Breakfast").get();
        for (DocumentSnapshot doc : docs.get().getDocuments()) {
            System.out.println(doc.getId());
            FoodFB foodFB = doc.toObject(FoodFB.class);
            if (foodFB.getId().equals(foodId)); {
                return Optional.of(foodFB);
            }
        }
        return Optional.empty();
    }

    /**
     * Get all foods in given collection.
     * @param collectionId which represents collection wanted.
     * @return list of food in collection.
     * @throws ExecutionException which is thrown when attempting to retrieve result of task that aborted.
     * @throws InterruptedException which is thrown when thread is interrupted either before or during activity.
     */
    public List<FoodFB> getAllFoodsInCollection(String collectionId) throws ExecutionException, InterruptedException {
        List<FoodFB> allFoodsInCollectionList = new ArrayList<>();
        Firestore firestoreDatabase = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> foods = firestoreDatabase.collection(collectionId).get();
        for (DocumentSnapshot doc : foods.get().getDocuments()) {
            FoodFB foodFB = doc.toObject(FoodFB.class);
            allFoodsInCollectionList.add(foodFB);
        }
        return allFoodsInCollectionList;
    }

    /**
     * Delete food with given id from database.
     * @param foodId which food to remove.
     * @return whether deletion was successful or not.
     * @throws ExecutionException which is thrown when attempting to retrieve result of task that aborted.
     * @throws InterruptedException which is thrown when thread is interrupted either before or during activity.
     */
    public String deleteFood(String foodId) throws ExecutionException, InterruptedException {
        Firestore firestoreDatabase = FirestoreClient.getFirestore();
        Optional<FoodFB> foodToDelete = getFoodById(foodId);
        if (foodToDelete.isEmpty()) {
            return "No food with given id";
        }
        FoodFB foodFB = foodToDelete.get();
        ApiFuture<WriteResult> docs = firestoreDatabase.collection("Breakfast").document(foodFB.getName()).delete();
        return "Successfully deleted food " + foodFB.getName();
    }
}
