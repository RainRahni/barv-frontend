package com.barv.firebase;

import com.barv.food.Food;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {
    public String saveFoodDetails(Food food) throws ExecutionException, InterruptedException {
        Firestore firestoreDatabase = FirestoreClient.getFirestore();
        return "";
    }
}
