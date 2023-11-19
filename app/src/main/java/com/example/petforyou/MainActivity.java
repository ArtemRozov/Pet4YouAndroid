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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private boolean isAuthorized;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        ApiService apiService = Api.getInsecureClient().create(ApiService.class);

        int userId = 3; // ID пользователя, которого вы хотите получить

        Call<UserModel> call = apiService.getData(userId);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    UserModel user = response.body();
                    if (user != null) {
                        // Обработка данных пользователя
                        int id = user.getId();
                        String login = user.getLogin();
                        String psw = user.getPasswordHash();
                        // ... и так далее
                        System.out.println("login = " + login);
                        System.out.println("psw = " + psw);
                    } else {
                        // Обработка ситуации, когда ответ от сервера пустой
                        System.out.println("22222222222222222222");
                    }
                } else {
                    // Обработка ошибок сервера (например, 404 или 500)
                    System.out.println("3333333333333333333333");
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                // Обработка ошибок сети или запроса
                System.out.println("4444444444444444444444");
                System.out.println("Ошибка: " + t.getMessage()); // Вывод текста ошибки
            }
        });





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