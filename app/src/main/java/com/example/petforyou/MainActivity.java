package com.example.petforyou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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





//        ApiService apiService = Api.getInsecureClient().create(ApiService.class);
//
//        int userId = 3; // ID пользователя, которого вы хотите получить
//
//        Call<UserModel> call = apiService.getData(userId);
//        call.enqueue(new Callback<UserModel>() {
//            @Override
//            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
//                if (response.isSuccessful()) {
//                    UserModel user = response.body();
//                    if (user != null) {
//                        // Обработка данных пользователя
//                        int id = user.getId();
//                        String login = user.getLogin();
//                        String psw = user.getPasswordHash();
//                        // ... и так далее
//                        System.out.println("login = " + login);
//                        System.out.println("psw = " + psw);
//                    } else {
//                        // Обработка ситуации, когда ответ от сервера пустой
//                        System.out.println("22222222222222222222");
//                    }
//                } else {
//                    // Обработка ошибок сервера (например, 404 или 500)
//                    System.out.println("3333333333333333333333");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserModel> call, Throwable t) {
//                // Обработка ошибок сети или запроса
//                System.out.println("4444444444444444444444");
//                System.out.println("Ошибка: " + t.getMessage()); // Вывод текста ошибки
//            }
//        });



        // Filling main page by the latest advertisement
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<AdvertisementModel> adsList = new ArrayList<>();
//        adsList.add(0, new AdvertisementModel(R.drawable.cat2, "Продам британську кішечку",
//                "Полтавська обл.", "15.09.2023", "11 000 грн"));
//        adsList.add(0, new AdvertisementModel(R.drawable.cat1, "Котик шукає родину",
//                "Донецька обл.", "вчора 20:48", "Безкоштовно"));
//        adsList.add(0, new AdvertisementModel(R.drawable.dog1, "Коргі - натуральний бобтейл!",
//                "Львівська обл.", "Сьогодні 12:07", "27 000 грн"));


        ApiService apiService = Api.getInsecureClient().create(ApiService.class);

        Call<List<Advertisement>> call = apiService.getAdvertisements(
                new AdvertisementFilterModel("sell",
                        null, null, null,
                        null, 0, 0, 0, 0));

        Intent intentAdv = getIntent();
        if (intentAdv.hasExtra("filterModel")) {
            AdvertisementFilterModel filterModel = (AdvertisementFilterModel) intentAdv.getSerializableExtra("filterModel");

            call = apiService.getAdvertisements(filterModel);
        }



        call.enqueue(new Callback<List<Advertisement>>() {
            @Override
            public void onResponse(Call<List<Advertisement>> call, Response<List<Advertisement>> response) {
                if (response.isSuccessful()) {
                    List<Advertisement> advertisements = response.body();
                    for (Advertisement advertisement : advertisements) {
                        AdvertisementInfoModel infoModel = advertisement.getAdvertisementInfo();
                        AdvertisementLocationModel locationModel = advertisement.getAdvertisementLocation();

                        // Далее вы можете использовать infoModel и locationModel по вашему усмотрению
                        // Например, добавить их в ваш список объявлений adsList
                        adsList.add(0, new AdvertisementModel(
                                R.drawable.cat2,
                                advertisement.getTitle(),
                                locationModel.getRegion(),
                                advertisement.getPublicationDate(),
                                Double.toString(infoModel.getPrice())
                        ));
                    }
                    // После завершения итерации по объявлениям обновите RecyclerView с вашим адаптером
                    //adapter.notifyDataSetChanged();
                    AdvertisementAdapter adapter = new AdvertisementAdapter(adsList);
                    recyclerView.setAdapter(adapter);
//                    adapter.setOnItemClickListener(advertisement -> {
//                        // Обработка клика на объявлении
//                        // Здесь вы можете запустить новую активность, передавая информацию об объявлении
//                        Intent intent = new Intent(MainActivity.this, FullAdvertisementActivity.class);
//                        intent.putExtra("advertisement", advertisement); // передача информации об объявлении
//                        startActivity(intent);
//                    });

                    //System.out.println("11111111111111111111111");
                    //System.out.println("adsList = "  + adsList.size());
                } else {
                    // Обработка ошибки
                    Toast.makeText(getApplicationContext(),
                            "Ошибка при получении объявлений", Toast.LENGTH_SHORT).show();
                    //System.out.println("2222222222222222222");
                }
            }

            @Override
            public void onFailure(Call<List<Advertisement>> call, Throwable t) {
                // Обработка ошибки при выполнении запроса
                Toast.makeText(getApplicationContext(),
                        "Ошибка сети", Toast.LENGTH_SHORT).show();
               // System.out.println("3333333333333333333333333");
                //System.out.println(t.getMessage());
            }
        });

        LinearLayout addButton = findViewById(R.id.addAdvertisementButton);
        addButton.setOnClickListener(view -> {
            Intent intent;
            intent = new Intent(getApplicationContext(), AddAdvertisementActivity.class);
            startActivity(intent);
        });

        Button filterButton = findViewById(R.id.button);
        filterButton.setOnClickListener(view -> {
            Intent intent;
            intent = new Intent(getApplicationContext(), FilterActivity.class);
            startActivity(intent);
        });

        LinearLayout likedButton = findViewById(R.id.likedButton);
        likedButton.setOnClickListener(view -> {
            Intent intent;
            intent = new Intent(getApplicationContext(), MyAdvertisements.class);
            startActivity(intent);
        });

        // handling profile button clicks
        LinearLayout profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(view -> {
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