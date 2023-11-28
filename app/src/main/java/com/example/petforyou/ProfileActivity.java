package com.example.petforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        UserModel user = (UserModel) getIntent().getSerializableExtra("userModel");

        TextView name = findViewById(R.id.nameTextView);
        name.setText("Name: " + user.getLogin().toString());

        Button button = findViewById(R.id.profileChangeButton);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ProfileChanging.class);
            startActivity(intent);
        });

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }

}