package com.example.petforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //TODO
        // need to add a check for the correctness of the entered data
        // if register button clicked
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
        });

        // if log in button clicked
        TextView textView = findViewById(R.id.textView3);
        textView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
            startActivity(intent);
        });
    }
}