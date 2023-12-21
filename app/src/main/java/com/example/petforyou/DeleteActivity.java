package com.example.petforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        int advertisementId = getIntent().getIntExtra("advertisementId", -1); // 0 - значение по умолчанию

        Button deleteButton = findViewById(R.id.button3);
        deleteButton.setOnClickListener(view -> {
            ApiService apiService = Api.getInsecureClient().create(ApiService.class);

            Call<Void> call = apiService.deleteAdvertisement(advertisementId);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        // Объявление успешно удалено
                        System.out.println("deleted");
                        Toast.makeText(DeleteActivity.this, "Объявление успешно удалено", Toast.LENGTH_SHORT).show();
                        // Дополнительные действия при успешном удалении...
                    } else {
                        System.out.println("not deleted2222222222");
                        // Обработка ошибки удаления
                        Toast.makeText(DeleteActivity.this, "Ошибка при удалении объявления", Toast.LENGTH_SHORT).show();
                        // Дополнительные действия при ошибке удаления...
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    System.out.println("not ggggggggggggggggggggggggg");
                    // Обработка ошибки сети или запроса
                    Toast.makeText(DeleteActivity.this, "Ошибка сети", Toast.LENGTH_SHORT).show();
                    // Дополнительные действия при ошибке сети...
                }
            });
            Intent intent;
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        Button cancelButton = findViewById(R.id.button4);
        cancelButton.setOnClickListener(view -> {
            Intent intent;
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}