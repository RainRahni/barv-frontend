package com.barv.firebase;

import com.barv.food.Food;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import javax.swing.text.Document;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {
    public String saveFoodDetails(Food food) throws ExecutionException, InterruptedException {
        Firestore firestoreDatabase = FirestoreClient.getFirestore();
        ApiFuture<DocumentSnapshot> collectionsApiFuture =
                firestoreDatabase.collection("Breakfast").document(food.getName()).get();
        return Objects.requireNonNull(collectionsApiFuture.get().getUpdateTime()).toString();
    }
}
