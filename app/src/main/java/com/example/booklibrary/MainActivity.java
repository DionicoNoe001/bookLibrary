package com.example.booklibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db= FirebaseFirestore.getInstance();

        Button calculateBtn = findViewById(R.id.calculateBtn);

        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView authorTextView = findViewById(R.id.authorTextView);
        TextView paymentTextView = findViewById(R.id.paymentTextView);

        EditText bookCodeTxtBox = findViewById(R.id.BookCodeTxtBox);
        EditText noOfDaysTxtBox = findViewById(R.id.noOfDaysTxtBox);

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bookCode = bookCodeTxtBox.getText().toString();
                Integer noOfDays = Integer.parseInt(noOfDaysTxtBox.getText().toString());


                db.collection("Books").document(bookCode).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Toast.makeText(MainActivity.this,bookCode, Toast.LENGTH_SHORT).show();

                        String bookTitle = documentSnapshot.getString("title");
                        String author = documentSnapshot.getString("author");
                        String type = documentSnapshot.getString("type");
                        Boolean isBorrowed = documentSnapshot.getBoolean("isBorrowed");

                        if(!bookCode.isEmpty() && type.equals("Premium")){

                            if (isBorrowed == true) {

                                Toast.makeText(MainActivity.this,"Book is already borrowed.", Toast.LENGTH_SHORT).show();

                            }else {

                                Book book = new PremiumBook();

                                String totalPrice = book.calculateBook(noOfDays).toString();

                                titleTextView.setText(bookTitle);
                                authorTextView.setText(author);
                                paymentTextView.setText(totalPrice);

                                DocumentReference docRef = db.collection("Books").document(bookCode);

                                Map<String, Object> updates = new HashMap<>();
                                updates.put("isBorrowed", true);

                                docRef.update(updates)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                // Update successful
                                                Log.d("Firestore", "isBorrowed field updated to true");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Update failed
                                                Log.w("Firestore", "Error updating isBorrowed field", e);
                                            }
                                        });
                            }

                        }else if(!bookCode.isEmpty() && type.equals("Regular")){

                            if (isBorrowed == true) {

                                Toast.makeText(MainActivity.this,"Book is already borrowed.", Toast.LENGTH_SHORT).show();

                            }else {

                                Book book = new RegularBook();

                                String totalPrice = book.calculateBook(noOfDays).toString();

                                titleTextView.setText(bookTitle);
                                authorTextView.setText(author);
                                paymentTextView.setText(totalPrice);

                                DocumentReference docRef = db.collection("Books").document(bookCode);

                                Map<String, Object> updates = new HashMap<>();
                                updates.put("isBorrowed", true);

                                docRef.update(updates)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                // Update successful
                                                Log.d("Firestore", "isBorrowed field updated to true");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Update failed
                                                Log.w("Firestore", "Error updating isBorrowed field", e);
                                            }
                                        });
                            }

                        }else {
                            Toast.makeText(MainActivity.this,"Book does not exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Firestore", "Error reading data from Firestore: " + e.getMessage());
                    }
                });

            }
        });

    }
}