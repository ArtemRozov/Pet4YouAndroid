package com.example.petforyou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private boolean isAuthorized;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Filling main page by the latest advertisement
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<AdvertisementModel> adsList = new ArrayList<>();
        adsList.add(0, new AdvertisementModel(R.drawable.cat2, "Продам британську кішечку",
                "Полтавська обл.", "15.09.2023", "11 000 грн"));
        adsList.add(0, new AdvertisementModel(R.drawable.cat1, "Котик шукає родину",
                "Донецька обл.", "вчора 20:48", "Безкоштовно"));
        adsList.add(0, new AdvertisementModel(R.drawable.dog1, "Коргі - натуральний бобтейл!",
                "Львівська обл.", "Сьогодні 12:07", "27 000 грн"));
        AdvertisementAdapter adapter = new AdvertisementAdapter(adsList);
        recyclerView.setAdapter(adapter);

        // handling profile button clicks
        LinearLayout myLinearLayout = findViewById(R.id.profileButton);
        myLinearLayout.setOnClickListener(view -> {
            Intent intent;
            if(isAuthorized){
                intent = new Intent(getApplicationContext(), ProfileActivity.class);
            } else{
                intent = new Intent(getApplicationContext(), LogInActivity.class);
            }
            startActivity(intent);
        });
    }
}