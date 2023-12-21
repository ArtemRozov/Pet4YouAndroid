package com.example.petforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        Spinner spinnerAdvertisementType = findViewById(R.id.spinner);
        String[] items = {"-", "Продаж", "Безкоштовно"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdvertisementType.setAdapter(adapter);

        Spinner spinnerPetType = findViewById(R.id.spinnerPetTypes);
        String[] itemsPetType = {"-", "Собака", "Кішка", "Рибка"};
        ArrayAdapter<String> adapterPetType = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemsPetType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPetType.setAdapter(adapterPetType);

        Spinner spinnerPetBreed = findViewById(R.id.spinnerPetBreed);
        String[] itemsPetBreed = {"-"};
        ArrayAdapter<String> adapterPetBreed = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemsPetBreed);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPetBreed.setAdapter(adapterPetBreed);

        Spinner spinnerPetGender = findViewById(R.id.spinnerGender);
        String[] itemsPetGender = {"-", "Чоловіча", "Жіноча"};
        ArrayAdapter<String> adapterPetGender = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemsPetGender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPetGender.setAdapter(adapterPetGender);

        spinnerPetType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                boolean itemSelected = position != 0;

                spinnerPetBreed.setEnabled(itemSelected);
                if (spinnerPetType.getSelectedItem().equals("Собака")) {
                    String[] itemsPetBreedDog = {"-", "Лабрадор", "Той-тер'єр", "Хаскі"};
                    ArrayAdapter<String> adapterPetBreedDog = new ArrayAdapter<>(FilterActivity.this, android.R.layout.simple_spinner_item, itemsPetBreedDog);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerPetBreed.setAdapter(adapterPetBreedDog);

                    spinnerPetGender.setEnabled(true);
                } else if (spinnerPetType.getSelectedItem().equals("Кішка")) {
                    String[] itemsPetBreedDog = {"-", "Британська", "Шотландська", "Сфінкс"};
                    ArrayAdapter<String> adapterPetBreedDog = new ArrayAdapter<>(FilterActivity.this, android.R.layout.simple_spinner_item, itemsPetBreedDog);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerPetBreed.setAdapter(adapterPetBreedDog);

                    spinnerPetGender.setEnabled(true);
                } else {
                    String[] itemsPetBreedDog = {"-", "Півник", "Скалярія", "Барбус"};
                    ArrayAdapter<String> adapterPetBreedDog = new ArrayAdapter<>(FilterActivity.this, android.R.layout.simple_spinner_item, itemsPetBreedDog);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerPetBreed.setAdapter(adapterPetBreedDog);

                    spinnerPetGender.setEnabled(false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerPetBreed.setEnabled(false);
            }
        });

        EditText editTextMinAge = findViewById(R.id.editTextAgeMin);
        EditText editTextMaxAge = findViewById(R.id.editTextAgeMax);
        EditText editTextMinPrice = findViewById(R.id.editTextPriceMin);
        EditText editTextMaxPrice = findViewById(R.id.editTextPriceMax);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            String advType = "Sell";
            String[] petTypes = null;
            String[] petBreeds = null;
            String gender = null;

            if (spinnerAdvertisementType.getSelectedItem().equals("Безкоштовно")) {
                advType = "Free";
            }

            if (spinnerPetType.getSelectedItem().equals("Кішка")) {
                petTypes = new String[]{"cat"};
            } else if (spinnerPetType.getSelectedItem().equals("Рибка")) {
                petTypes = new String[]{"fish"};
            } else if (spinnerPetType.getSelectedItem().equals("Собака")) {
                petTypes = new String[]{"dog"};
            }

            if (spinnerPetBreed.getSelectedItem().equals("Лабрадор")) {
                petBreeds = new String[]{"Labrador"};
            } else if (spinnerPetBreed.getSelectedItem().equals("Той-тер'єр")) {
                petBreeds = new String[]{"Toy terier"};
            } else if (spinnerPetBreed.getSelectedItem().equals("Хаскі")) {
                petBreeds = new String[]{"Haski"};
            }

            if (spinnerPetGender.getSelectedItem().equals("Чоловіча")) {
                gender = "male";
            } else if (spinnerPetGender.getSelectedItem().equals("Жіноча")) {
                gender = "female";
            }

            AdvertisementFilterModel filterModel = new AdvertisementFilterModel(
                    advType,
                    petTypes,
                    petBreeds,
                    null,
                    gender,
                    Integer.parseInt(editTextMinAge.getText().toString()),
                    Integer.parseInt(editTextMaxAge.getText().toString()),
                    Integer.parseInt(editTextMinPrice.getText().toString()),
                    Integer.parseInt(editTextMaxPrice.getText().toString())
            );

            Intent intent;
            intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("filterModel", filterModel);
            startActivity(intent);
        });
    }
}