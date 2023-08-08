package com.barv.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseInit {
    @PostConstruct
    private void initializeDatabase() throws IOException {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream
                            ("barvBackEnd/src/main/resources/barv-9d9ab-firebase-adminsdk-9ku5g-be9fdebca1.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://barv-9d9ab-default-rtdb.europe-west1.firebasedatabase.app")
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Firestore getFirebase() {
        return FirestoreClient.getFirestore();
    }
}
