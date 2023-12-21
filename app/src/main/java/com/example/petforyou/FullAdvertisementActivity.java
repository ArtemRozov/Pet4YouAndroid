package com.example.petforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FullAdvertisementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_advertisement);

        AdvertisementModel advertisement = getIntent().getParcelableExtra("advertisement");
    }
}