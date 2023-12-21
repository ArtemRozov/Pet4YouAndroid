package com.example.petforyou;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAdvertisementActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advertisement);

        Spinner spinner = findViewById(R.id.spinnerOptions);
        String[] options = {"Продажа", "Безкоштовно", "Вариант 3"};

        Spinner spinnerPetType = findViewById(R.id.petTypeSpinnerOptions);
        String[] optionsPet = {"Dog", "Cat", "Fish"};

        Spinner spinnerGender = findViewById(R.id.genderSpinnerOptions);
        String[] optionsGender = {"Male", "Female"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, optionsPet);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, optionsGender);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinnerPetType.setAdapter(adapter2);
        spinnerGender.setAdapter(adapter3);

        Button addButton =  findViewById(R.id.addButton);
        addButton.setOnClickListener(view -> {
            TextView titleTextView =  findViewById(R.id.titleTextView);
            TextView countryTextView =  findViewById(R.id.countryTextView);
            TextView cityTextView =  findViewById(R.id.cityTextView);
            TextView regionTextView =  findViewById(R.id.regionTextView);
            TextView breedTextView =  findViewById(R.id.breedTextView);
            TextView ageTextView =  findViewById(R.id.ageTextView);
            TextView priceTextView =  findViewById(R.id.priceTextView);
            TextView descriptionTextView =  findViewById(R.id.descriptionTextView);
            String petType = "sell";
            if(spinnerPetType.getSelectedItem().toString().equals("Безкоштовно")){
                petType = "free";
            }
            String advType = "dog";
            if(spinner.getSelectedItem().toString().equals("Cat")){
                advType = "cat";
            }
            String gender = "male";
            if(spinnerGender.getSelectedItem().toString().equals("Female")){
                gender = "female";
            }

            ApiService apiService = Api.getInsecureClient().create(ApiService.class);

            Advertisement advertisement = new Advertisement(0, titleTextView.getText().toString(),
                    petType, LocalDateTime.now().toString(),
                    4, descriptionTextView.getText().toString(),
                    advType,
                    false);

            AdvertisementLocationModel locationModel = new AdvertisementLocationModel(0,
                    countryTextView.getText().toString(), cityTextView.getText().toString(),
                    regionTextView.getText().toString());
            AdvertisementInfoModel infoModel = new AdvertisementInfoModel(0,
                    breedTextView.getText().toString(), Integer.parseInt(ageTextView.getText().toString()),
                    Integer.parseInt(priceTextView.getText().toString()), gender);

            advertisement.setAdvertisementLocation(locationModel);
            advertisement.setAdvertisementInfo(infoModel);

            Call<Void> call = apiService.addAdvertisement(advertisement);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        // Обработка успешного добавления
                        Toast.makeText(getApplicationContext(),
                                "Успішно додано", Toast.LENGTH_SHORT).show();
                        System.out.println("1111111111111111");
                    } else {
                        // Обработка ошибки
                        Toast.makeText(getApplicationContext(),
                                "Помилка при додаванні", Toast.LENGTH_SHORT).show();
                        System.out.println("222222222222");
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // Обработка ошибки при выполнении запроса
                    Toast.makeText(getApplicationContext(),
                            "Помилка мережі", Toast.LENGTH_SHORT).show();
                    System.out.println("3333333333333333333");
                }
            });


            Intent intent;
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}